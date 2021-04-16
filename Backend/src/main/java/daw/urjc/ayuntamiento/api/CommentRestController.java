package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.Comment;
import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.service.CommentService;
import daw.urjc.ayuntamiento.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    @Autowired
    private CommentService comments;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Collection<Comment> getComments(){
        return comments.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable long id){
        Optional<Comment> comment = comments.findId(id);
        if (comment.isPresent()){
            return ResponseEntity.ok(comment.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable long id) throws SQLException {
        Optional<Comment> comment = comments.findId(id);
        if (comment.isPresent()){
            int profilePhotoLength = (int) comment.get().getImageFile().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(comment.get().getImageFile().getBytes(1, profilePhotoLength)));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());

        comment.setName(user.get().getName());
        Date date= new Date();
        comment.setDate(date);

        comment.setImageFile(user.get().getImageFile());

        comments.save(comment);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(location).body(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable long id){
        Optional<Comment> comment = comments.findId(id);
        if(comment.isPresent()){
            comments.delete(id);
            return ResponseEntity.ok(comment.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> replaceComment(@PathVariable long id, @org.springframework.web.bind.annotation.RequestBody Comment newComment) {
        Optional<Comment> comment = comments.findId(id);
        if (comment.isPresent()) {
            newComment.setId(id);
            comments.save(newComment);
            return ResponseEntity.ok(comment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
