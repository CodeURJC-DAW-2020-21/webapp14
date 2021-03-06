package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.service.CommentService;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.LocalService;
import daw.urjc.ayuntamiento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private LocalService localService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private EventService eventService;


    @GetMapping("/")
    public String indexLink(Model model) {
        long users = userService.count();
        long locals = localService.count();
        long events = eventService.count();
        long comments = commentService.count();
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

    @RequestMapping("/locals")
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
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());
        int commentsnumber = user.get().getComment().size();
        if(commentsnumber>=100){
            model.addAttribute("commentbadge","commentbadge5.png");
            model.addAttribute("commentlevel","5");
        } else if(commentsnumber>=50){
            model.addAttribute("commentbadge","commentbadge4.png");
            model.addAttribute("commentlevel","4");
        } else if(commentsnumber>=25){
            model.addAttribute("commentbadge","commentbadge3.png");
            model.addAttribute("commentlevel","3");
        } else if(commentsnumber>=10){
            model.addAttribute("commentbadge","commentbadge2.png");
            model.addAttribute("commentlevel","2");
        } else if(commentsnumber>=5){
            model.addAttribute("commentbadge","commentbadge1.png");
            model.addAttribute("commentlevel","1");
        }

        int subscribenumber = user.get().getEventSuscribe().size();
        if(subscribenumber>=25){
            model.addAttribute("subscribebadge","subscribebadge5.png");
            model.addAttribute("subscribelevel","5");
        } else if(subscribenumber>=20){
            model.addAttribute("subscribebadge","subscribebadge4.png");
            model.addAttribute("subscribelevel","4");
        } else if(subscribenumber>=15){
            model.addAttribute("subscribebadge","subscribebadge3.png");
            model.addAttribute("subscribelevel","3");
        } else if(subscribenumber>=10){
            model.addAttribute("subscribebadge","subscribebadge2.png");
            model.addAttribute("subscribelevel","2");
        } else if(subscribenumber>=5){
            model.addAttribute("subscribebadge","subscribebadge1.png");
            model.addAttribute("subscribelevel","1");
        }

        String tagaux="";
        int valaux = 0;

        Map<String, Integer> mapaux = user.get().getMap();
        if(mapaux.get("Deporte")>valaux){
            valaux=mapaux.get("Deporte");
            tagaux="Deporte";
        }
        if(mapaux.get("Cultura")>valaux){
            valaux=mapaux.get("Cultura");
            tagaux="Cultura";
        }
        if(mapaux.get("Musica")>valaux){
            valaux=mapaux.get("Musica");
            tagaux="Musica";
        }
        if(mapaux.get("Videojuegos")>valaux){
            valaux=mapaux.get("Videojuegos");
            tagaux="Videojuegos";
        }
        List<Event> eventsList = eventService.findAllByTag1(tagaux);
        List<Long> userEvents = user.get().getEventSuscribe();
        LinkedList<Event> eventListAux = new LinkedList<>();
        for (int i = 0; i < eventsList.size(); i++) {
            eventListAux.add(eventsList.get(i));
        }
        for (int i = 0; i < eventsList.size(); i++) {
            Event eventAux = eventsList.get(i);
            Long idAux = eventAux.getId();
            if(userEvents.contains(idAux)){
                eventListAux.remove(eventAux);
            }
        }
        if (!eventListAux.isEmpty()){
            int randomNumber = (int) Math.floor(Math.random()*((eventListAux.size())));;
            while(user.get().getEventSuscribe().contains(eventListAux.get(randomNumber).getId()) && eventListAux.size()>1){
                randomNumber = (int) Math.floor(Math.random()*((eventListAux.size())));
            }
            Event recommendedEvent = eventListAux.get(randomNumber);
            model.addAttribute("recommendedEvent",recommendedEvent);
        }
        return "profile";
    }





}