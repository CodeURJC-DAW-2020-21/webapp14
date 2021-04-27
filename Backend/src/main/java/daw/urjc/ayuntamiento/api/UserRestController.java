package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.service.UserService;
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
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService users;

    @GetMapping("/")
    public Collection<User> getUsers() {
        return users.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> user = users.findId(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        Optional<User> user = users.findId(id);
        if (user.isPresent()) {
            users.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        users.save(user);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }




    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable long id) throws SQLException {

        Optional<User> user = users.findId(id);

        if (user.isPresent()) {
            int profilePhotoLength = (int) user.get().getImageFile().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(user.get().getImageFile().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }


    @PostMapping("/{id}/image")
    public ResponseEntity<User> postImage (@ModelAttribute UserDTO userDTO, @PathVariable long id) throws IOException, SQLException {


        User user = users.findId(id).get();
        MultipartFile img = userDTO.getImageField();
        user.setImageFile(BlobProxy.generateProxy(img.getInputStream(), img.getSize()));
        users.save(user);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@PathVariable long id, @RequestBody User newUser) {
        Optional<User> user = users.findId(id);
        if (user.isPresent()) {
            newUser.setId(id);
            users.save(newUser);
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<User> replaceImage(@ModelAttribute UserDTO userDTO, @PathVariable long id) throws IOException, SQLException {
        Optional<User> user = users.findId(id);
        if(user.isPresent()){
            MultipartFile img1 = userDTO.getImageField();
            user.get().setImageFile(BlobProxy.generateProxy(img1.getInputStream(), img1.getSize()));
            users.save(user.get());
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.get().getId()).toUri();
            return ResponseEntity.created(location).body(user.get());
        }else

            return ResponseEntity.notFound().build();
    }





}
