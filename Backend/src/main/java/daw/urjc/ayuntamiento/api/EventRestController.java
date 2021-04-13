package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
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
/*
    @PostMapping("/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {
        Optional<Event> event = events.findId(id);
        if (event.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();
            event.get().setImageFile(location.toString());
            events.save(event.get());
            imgService.saveImage(POSTS_FOLDER, event.get().getId(), imageFile);
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
*/
}
