package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.repository.CommentRepository;
import daw.urjc.ayuntamiento.repository.EventRepository;
import daw.urjc.ayuntamiento.repository.StoreRepository;
import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.repository.UserRepository;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private StoreRepository LocalRepository;

    @Autowired
    private EventRepository EventRepository;

    @Autowired
    private CommentRepository CommentRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private LocalService localService;

    @GetMapping("/")
    public String indexLink(Model model) {
        long users = UserRepository.count();
        long locals = LocalRepository.count();
        long events = EventRepository.count();
        long comments = CommentRepository.count();
        model.addAttribute("users", users);
        model.addAttribute("locals", locals);
        model.addAttribute("events", events);
        model.addAttribute("comments", comments);
        return "index";
    }

    @GetMapping("/gob")
    public String gobLink(Model model) {
        return "gobernTeam";
    }

    @RequestMapping("/events")
    public String eventsLink(Model model, Pageable pageable) {

        Page<Event> eventPage = eventService.findEvents(pageable);
        model.addAttribute("hasNext",eventPage.hasNext());
        model.addAttribute("event", eventPage);


        return "events";
    }

    @GetMapping("/locals")
    public String localsLink(Model model, Pageable pageable) {

        Page<Store> localsPage = localService.findLocals(pageable);
        model.addAttribute("hasNext",localsPage.hasNext());
        model.addAttribute("local",localsPage);
        return "locals";
    }

    @GetMapping("/error")
    public String errorLink(Model model) {
        return "error";
    }

    @GetMapping("/profile")
    public String profile_page(Model model,HttpServletRequest request){
        return "profile";
    }




}