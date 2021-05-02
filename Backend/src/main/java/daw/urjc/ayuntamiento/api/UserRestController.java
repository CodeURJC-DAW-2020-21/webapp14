package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.service.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordEncoder passwordencoder;

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
    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return ResponseEntity.ok(users.findByName(principal.getName()).orElseThrow());
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
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        System.out.println("**************");
        User user = new User(userDTO.getName(), userDTO.getMail(), userDTO.getDescription(), userDTO.getDNI()," ");


        user.setPassword(passwordencoder.encode(userDTO.getPassword()));
        System.out.println(user.getPassword());
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
    public ResponseEntity<User> replaceUser(@PathVariable long id, @RequestBody UserDTO newUserDTO) throws IOException, SQLException {
        Optional<User> user = users.findId(id);
        if (user.isPresent()) {
            User newUser = new User(newUserDTO.getName(), newUserDTO.getMail(), newUserDTO.getDescription(),newUserDTO.getDNI(),"");
            newUser.setRoles(newUserDTO.getRoles());
            newUser.setId(user.get().getId());
            newUser.setComment(newUserDTO.getComment());
            newUser.setCommentPlaces(newUserDTO.getCommentPlaces());
            newUser.setEvents(newUserDTO.getEvents());
            newUser.setEventSuscribe(newUserDTO.getEventSuscribe());
            newUser.setPassword(passwordEncoder.encode(user.get().getPassword()));
            users.save(newUser);
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/try")
    public ResponseEntity<User> updateBook(@PathVariable long id, @RequestBody User updatedBook) throws SQLException {

        if (users.exist(id)) {

                // Maintain the same image loading it before updating the book
                User dbBook = users.findId(id).orElseThrow();
                    updatedBook.setImageFile(BlobProxy.generateProxy(dbBook.getImageFile().getBinaryStream(),
                            dbBook.getImageFile().length()));



            updatedBook.setId(id);
            updatedBook.setPassword(passwordEncoder.encode(dbBook.getPassword()));
            users.save(updatedBook);

            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else	{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
