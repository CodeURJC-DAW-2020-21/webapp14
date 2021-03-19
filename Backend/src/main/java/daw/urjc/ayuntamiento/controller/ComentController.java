package daw.urjc.ayuntamiento.controller;


import daw.urjc.ayuntamiento.modules.Comment;
import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.service.CommentService;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@Controller
public class ComentController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/createCommentEvents/{id}")
    public String commentcreation(Comment comment, Model model, HttpServletRequest request,@PathVariable long id) throws IOException, SQLException {
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());

        Comment commentaux = new Comment(comment.getText());
        commentaux.setName(user.get().getName());
        Date date= new Date();
        commentaux.setDate(date);

        commentaux.setImageFile(user.get().getImageFile());

        commentService.save(commentaux);


        Optional<Event> event = eventService.findId(id);
        event.get().getComment().add(commentaux);
        //Event eventObject = new Event(event.get().getName(),event.get().getActivities(),event.get().getDescription(),event.get().getDate(),event.get().getPlace(),event.get().getReward(),event.get().getPeople(),event.get().getPrice());
        //eventObject.setImageFile(event.get().getImageFile());
        //eventObject.setComment(event.get().getComment());
        //eventService.delete(id);
        Event eventAux = event.get();
        eventService.save(eventAux);
        model.addAttribute("event",eventService.findAll());
        return "events";

    }

    @GetMapping("/comment/{name}")
    public ResponseEntity<Object> giveUserImage(@PathVariable String name) throws SQLException {
        Optional<Comment> comment = commentService.findName(name);
        if(comment.isPresent() && comment.get().getImageFile() != null){
            Resource file = new InputStreamResource(comment.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(comment.get().getImageFile().length()).body(file);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
