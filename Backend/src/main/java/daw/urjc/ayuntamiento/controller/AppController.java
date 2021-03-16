package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.repository.EventRepository;
import daw.urjc.ayuntamiento.repository.UserRepository;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.LocalService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.Time;
import java.util.Date;

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
        return "agent";
    }

    @GetMapping("/events")
    public String eventsLink(Model model) {

        model.addAttribute("event",eventService.findAll());

        return "properties";
    }

    @GetMapping("/locals")
    public String localsLink(Model model) {

        model.addAttribute("local",localService.findAll());
        return "blog";
    }

    @GetMapping("/error")
    public String errorLink(Model model) {
        return "error";
    }

    @GetMapping("/profile")
    public String profile_page(Model model){
        return "perfil";
    }


    @PostMapping("/registeredUser")
    public String userRegister (@RequestParam String name, @RequestParam String DNI, @RequestParam String mail, @RequestParam String password, @RequestParam String description, Model model){
        User user = new User(name,mail,description,DNI,password,"USER");
        repository.save(user);
        model.addAttribute("user",user);
        return "perfil";
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