package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.Optional;

@Controller
public class LocalController {

    @Autowired
    private LocalService service;

    @GetMapping("/local/{id}")
    public String giveLocal(Model model, @PathVariable long id){
        Optional<Store> local = service.findId(id);

        if (local.isPresent()){
            model.addAttribute("local",local.get());
            return "mainLocal";
        }
        return "locals";
    }

    @GetMapping("/local/{id}/image1")
    public ResponseEntity<Object> giveImageLocal1(@PathVariable long id) throws SQLException {
        Optional<Store> store = service.findId(id);
        if(store.isPresent() && store.get().getImageField1() != null){
            Resource file = new InputStreamResource(store.get().getImageField1().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(store.get().getImageField1().length()).body(file);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/local/{id}/image2")
    public ResponseEntity<Object> giveImageLocal2(@PathVariable long id) throws SQLException {
        Optional<Store> store = service.findId(id);
        if(store.isPresent() && store.get().getImageField2() != null){
            Resource file = new InputStreamResource(store.get().getImageField2().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(store.get().getImageField2().length()).body(file);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/removeLocal/{id}")
    public String removeLocal(Model model, @PathVariable long id) {

        Optional<Store> store = service.findId(id);
        if (store.isPresent()) {
           service.delete(id);
            model.addAttribute("local",service.findAll());
        }
        return "locals";
    }



}
