package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/store")
public class StoreRestController {
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

    @PostMapping("/")
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        service.save(store);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(store.getId()).toUri();
        return ResponseEntity.created(location).body(store);
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

}
