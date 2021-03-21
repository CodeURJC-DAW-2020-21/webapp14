package daw.urjc.ayuntamiento.controller;


import daw.urjc.ayuntamiento.modules.Comment;
import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.service.CommentService;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.LocalService;
import daw.urjc.ayuntamiento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private EventService eventService;

    @Autowired
    private LocalService localService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/createCommentEvents/{id}")
    public String commentcreation(Comment comment, Model model, Pageable pageable, HttpServletRequest request, @PathVariable long id) throws IOException, SQLException {
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
        user.get().getComment().add(commentaux);
        user.get().getCommentPlaces().add(event.get().getName());
        Event eventAux = event.get();
        eventService.save(eventAux);
        Page<Event> eventPage = eventService.findEvents(pageable);
        model.addAttribute("hasNext",eventPage.hasNext());
        model.addAttribute("event", eventPage);
        return "events";

    }
    @PostMapping("/createCommentLocal/{id}")
    public String commentcreationLocal(Comment comment, Model model, Pageable pageable, HttpServletRequest request, @PathVariable long id) throws IOException, SQLException {
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());

        Comment commentaux = new Comment(comment.getText());
        commentaux.setName(user.get().getName());
        Date date= new Date();
        commentaux.setDate(date);

        commentaux.setImageFile(user.get().getImageFile());

        commentService.save(commentaux);


        Optional<Store> local = localService.findId(id);
        local.get().getComment().add(commentaux);
        user.get().getComment().add(commentaux);
        user.get().getCommentPlaces().add(local.get().getName());
        Store localaux = local.get();
        localService.save(localaux);
        Page<Store> localsPage = localService.findLocals(pageable);
        model.addAttribute("hasNext",localsPage.hasNext());
        model.addAttribute("local",localsPage);
        return "locals";

    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<Object> giveUserImage(@PathVariable long id) throws SQLException {
        Optional<Comment> comment = commentService.findId(id);
        if(comment.isPresent() && comment.get().getImageFile() != null){
            Resource file = new InputStreamResource(comment.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(comment.get().getImageFile().length()).body(file);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
