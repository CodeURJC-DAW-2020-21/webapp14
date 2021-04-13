package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.Comment;
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
import java.util.LinkedList;
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

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable long id) throws SQLException {

        Optional<Store> store = service.findId(id);

        if (store.isPresent()) {
            int profilePhotoLength = (int) store.get().getImageField1().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(store.get().getImageField1().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    public ResponseEntity<Store> createStore(@ModelAttribute StoreDTO storeDTO) throws IOException, SQLException {
        Store store = new Store(storeDTO.getName(),storeDTO.getDescription(),storeDTO.getFrontdescription(),storeDTO.getServices(),storeDTO.getOpenDay(),storeDTO.getCloseDay(),storeDTO.getOpenHour(),storeDTO.getCloseHour(),storeDTO.getStreet(),storeDTO.getLatitude(),storeDTO.getLength());
        store.setComment(new LinkedList<Comment>());
        MultipartFile img1 = storeDTO.getImageField1();
        MultipartFile img2 = storeDTO.getImageField2();
        store.setImageField1(BlobProxy.generateProxy(img1.getInputStream(), img1.getSize()));
        service.save(store);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(store.getId()).toUri();

        return ResponseEntity.created(location).body(store);
    }
}
