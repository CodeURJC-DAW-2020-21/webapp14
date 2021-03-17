package daw.urjc.ayuntamiento.controller;


import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.service.CommentService;
import daw.urjc.ayuntamiento.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ComentController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

}
