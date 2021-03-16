package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.service.EventService;
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
        Optional<Store> store = service.findId(id);

        if (store.isPresent()){
            model.addAttribute("local",store.get());
            return "store";
        }
        return "blog";
    }

    @GetMapping("/local/{id}/image")
    public ResponseEntity<Object> giveImageEvent(@PathVariable long id) throws SQLException {
        Optional<Store> store = service.findId(id);
        if(store.isPresent() && store.get().getImageFile1() != null){
            Resource file = new InputStreamResource(store.get().getImageFile1().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(store.get().getImageFile1().length()).body(file);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
