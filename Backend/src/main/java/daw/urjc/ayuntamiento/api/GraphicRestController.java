package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.Comment;
import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.service.CommentService;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.LocalService;
import daw.urjc.ayuntamiento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/graphics")
public class GraphicRestController {

    @Autowired
    private EventService events;

    @Autowired
    private UserService users;

    @Autowired
    private LocalService stores;

    @Autowired
    private CommentService comments;



    @GetMapping("/events")
    public long getEventsNumber() {
        long event = events.count();
        return event;

    }

    @GetMapping("/stores")
    public long getStoreNumber() {
        long store = stores.count();
        return store;
    }

    @GetMapping("/users")
    public long getUserNumber() {
        long user = users.count();
        return user;
    }

    @GetMapping("/comments")
    public long getCommentUNumber() {
        long comment = comments.count();
        return comment;
    }

}
