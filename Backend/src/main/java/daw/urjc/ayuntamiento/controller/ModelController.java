package daw.urjc.ayuntamiento.controller;

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

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("userDesc", userService.findByName(principal.getName()).get().getDescription());
            model.addAttribute("userMail", userService.findByName(principal.getName()).get().getMail());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("eventlist",userService.findByName(principal.getName()).get().getEvents());
            model.addAttribute("commentlist",userService.findByName(principal.getName()).get().getCommentPlaces());


        } else {
            model.addAttribute("logged", false);
        }
    }
}
