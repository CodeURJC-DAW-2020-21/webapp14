package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private UserRepository repository;

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



    @GetMapping("/events/basket")
    public String basketLink(Model model) {
        return "Baloncesto";
    }

    @GetMapping("/locals/camachito")
    public String camachitoLink(Model model){
        return "Camachito";
    }

    @GetMapping("/error")
    public String errorLink(Model model) {
        return "Error";
    }

    @GetMapping("/FormEvent")
    public String formularioEventLink(Model model){
        return "FormularioEventos";
    }

    @GetMapping("/FormLocal")
    public String formularioLocalLink(Model model){
        return "FormularioLocal";
    }

    @PostMapping("/registeredUser")
    public String userRegister (@RequestParam String name, @RequestParam String DNI, @RequestParam String mail, @RequestParam String password, @RequestParam String description, Model model){
        User user = new User(name,mail,description,DNI,password,"user");
        repository.save(user);
        model.addAttribute("user",user);
        return "Perfil";
    }
}