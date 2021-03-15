package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.repository.EventRepository;
import daw.urjc.ayuntamiento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Blob;
import java.sql.Time;
import java.util.Date;

@Controller
public class AppController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EventRepository repositoryEvent;

    @GetMapping("/")
    public String indexLink(Model model) {
        return "index";
    }

    @GetMapping("/gob")
    public String gobLink(Model model) {
        return "agent";
    }

    @GetMapping("/events")
    public String eventsLink(Model model) {
        return "properties";
    }

    @GetMapping("/locals")
    public String localsLink(Model model) {
        return "blog";
    }

    @GetMapping("/error")
    public String errorLink(Model model) {
        return "error";
    }

    @RequestMapping("/FormEvent")
    public String formularioEventLink(Model model){
        return "formularioEventos";
    }

    @GetMapping("/FormLocal")
    public String formularioLocalLink(Model model){
        return "formularioLocal";
    }

    @GetMapping("/profile")
    public String profile_page(Model model){
        return "perfil";
    }


    @PostMapping("/registeredUser")
    public String userRegister (@RequestParam String name, @RequestParam String DNI, @RequestParam String mail, @RequestParam String password, @RequestParam String description, Model model){
        User user = new User(name,mail,description,DNI,password,"user");
        repository.save(user);
        model.addAttribute("user",user);
        return "perfil";
    }

    @PostMapping("/createEvent")
    public String eventcreation(@RequestParam String name,
                                @RequestParam String activities,
                                @RequestParam String description,
                                @RequestParam Date date,
                                @RequestParam String place,
                                @RequestParam Time time,
                                @RequestParam String reward,
                                @RequestParam String people,
                                @RequestParam String price,
                                @RequestParam Blob imageFile, Model model) {

        Event event = new Event(name,activities,description,date,place,time,reward,people,price,imageFile);
        repositoryEvent.save(event);
        model.addAttribute("event",event);
        return "eventosMustache";
    }

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("user", repository.findByDNI(principal.getName()));
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            //model.addAttribute("user",repository.findById(id));

        } else {
            model.addAttribute("logged", false);
        }
    }
}