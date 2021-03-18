package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.repository.UserRepository;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.LocalService;
import daw.urjc.ayuntamiento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@ControllerAdvice
public class ModelController {


    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private LocalService localService;



    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("userDesc", userService.findByName(principal.getName()).get().getDescription());
            model.addAttribute("userMail", userService.findByName(principal.getName()).get().getMail());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));


        } else {
            model.addAttribute("logged", false);
        }
    }
}
