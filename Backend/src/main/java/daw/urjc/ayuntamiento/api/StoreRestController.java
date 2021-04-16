package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.service.LocalService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/store")
public class StoreRestController {
    private static final String POSTS_FOLDER = "service";
    @Autowired
    private LocalService service;

    @GetMapping("/")
    public Collection<Store> getStores(){
       return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStore(@PathVariable long id) {
        Optional<Store> store = service.findId(id);
        if (store.isPresent()) {
            return ResponseEntity.ok(store.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Store> deleteStore(@PathVariable long id) {
        Optional<Store> store = service.findId(id);
        if (store.isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(store.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> replaceStore(@PathVariable long id, @RequestBody Store newStore) {
        Optional<Store> store = service.findId(id);
        if (store.isPresent()) {
            newStore.setId(id);
            service.save(newStore);
            return ResponseEntity.ok(store.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/image1")
    public ResponseEntity<Object> getImage1(@PathVariable long id) throws SQLException {

        Optional<Store> store = service.findId(id);

        if (store.isPresent()) {
            int profilePhotoLength = (int) store.get().getImageField1().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(store.get().getImageField1().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/image2")
    public ResponseEntity<Object> getImage2(@PathVariable long id) throws SQLException {

        Optional<Store> store = service.findId(id);

        if (store.isPresent()) {
            int profilePhotoLength = (int) store.get().getImageField2().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(store.get().getImageField1().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    ResponseEntity<Store> createStore(@RequestBody Store store) {
        service.save(store);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(store.getId()).toUri();
        return ResponseEntity.created(location).body(store);
    }

    @PostMapping("/{id}/image1")
    public ResponseEntity<Store> postImage1(@ModelAttribute StoreDTO storeDTO, @PathVariable long id) throws IOException, SQLException {


        Store store = service.findId(id).get();
        MultipartFile img1 = storeDTO.getImageField1();
        store.setImageField1(BlobProxy.generateProxy(img1.getInputStream(), img1.getSize()));
        service.save(store);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(store.getId()).toUri();

        return ResponseEntity.created(location).body(store);
    }

    @PostMapping("/{id}/image2")
    public ResponseEntity<Store> postImage2(@ModelAttribute StoreDTO storeDTO, @PathVariable long id) throws IOException, SQLException {
        Store store = service.findId(id).get();
        MultipartFile img2 = storeDTO.getImageField2();
        store.setImageField2(BlobProxy.generateProxy(img2.getInputStream(), img2.getSize()));
        service.save(store);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(store.getId()).toUri();

        return ResponseEntity.created(location).body(store);
    }

    @PutMapping("/{id}/image1")
    public ResponseEntity<Store> replaceImage1(@ModelAttribute StoreDTO storeDTO, @PathVariable long id) throws IOException, SQLException {
        Optional<Store> store = service.findId(id);
        if(store.isPresent()){
            MultipartFile img1 = storeDTO.getImageField1();
            store.get().setImageField1(BlobProxy.generateProxy(img1.getInputStream(), img1.getSize()));
            service.save(store.get());
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(store.get().getId()).toUri();

            return ResponseEntity.created(location).body(store.get());
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/image2")
    public ResponseEntity<Store> replaceImage2(@ModelAttribute StoreDTO storeDTO, @PathVariable long id) throws IOException, SQLException {
        Optional<Store> store = service.findId(id);
        if(store.isPresent()){
            MultipartFile img2 = storeDTO.getImageField2();
            store.get().setImageField1(BlobProxy.generateProxy(img2.getInputStream(), img2.getSize()));
            service.save(store.get());
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(store.get().getId()).toUri();

            return ResponseEntity.created(location).body(store.get());
        } else
            return ResponseEntity.notFound().build();
    }
}
