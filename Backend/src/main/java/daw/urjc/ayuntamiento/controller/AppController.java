package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.Event;
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
    private UserRepository repository;

    @Autowired
    private EventService eventService;

    @Autowired
    private LocalService localService;

    @GetMapping("/")
    public String indexLink(Model model) {
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
    public String localsLink(Model model) {

        model.addAttribute("local",localService.findAll());
        return "locals";
    }

    @GetMapping("/error")
    public String errorLink(Model model) {
        return "error404";
    }

    @GetMapping("/profile")
    public String profile_page(Model model,HttpServletRequest request){
        return "profile";
    }




}