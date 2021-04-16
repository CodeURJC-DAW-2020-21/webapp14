package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.service.EventService;
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
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/events")
public class EventRestController {
    @Autowired
    private EventService events;

    @GetMapping("/")
    public Collection<Event> getEvents() {
        return events.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Event> getPost(@PathVariable long id) {
        Optional<Event> event = events.findId(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        events.save(event);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(location).body(event);
    }
    @PostMapping("/{id}/image")
    public ResponseEntity<Event> postImage(@ModelAttribute EventDTO eventDTO, @PathVariable long id) throws IOException, SQLException {


        Event event = events.findId(id).get();
        MultipartFile img1 = eventDTO.getImageFile();
        event.setImageFile(BlobProxy.generateProxy(img1.getInputStream(), img1.getSize()));
        events.save(event);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();

        return ResponseEntity.created(location).body(event);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable long id) throws SQLException {

        Optional<Event> event = events.findId(id);

        if (event.isPresent()) {
            int profilePhotoLength = (int) event.get().getImageFile().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(event.get().getImageFile().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable long id) {
        Optional<Event> event = events.findId(id);
        if (event.isPresent()) {
            events.delete(id);
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> replacePost(@PathVariable long id, @RequestBody Event newPost) {
        Optional<Event> event = events.findId(id);
        if (event.isPresent()) {
            newPost.setId(id);
            events.save(newPost);
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/image")
    public ResponseEntity<Event> replaceImage(@ModelAttribute EventDTO eventDTO, @PathVariable long id) throws IOException, SQLException {
        Optional<Event> event = events.findId(id);
        if(event.isPresent()){
            MultipartFile img1 = eventDTO.getImageFile();
            event.get().setImageFile(BlobProxy.generateProxy(img1.getInputStream(), img1.getSize()));
            events.save(event.get());
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(event.get().getId()).toUri();

            return ResponseEntity.created(location).body(event.get());
        } else
            return ResponseEntity.notFound().build();
    }
}
