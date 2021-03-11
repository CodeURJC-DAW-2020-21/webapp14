package daw.urjc.ayuntamiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

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

    @GetMapping("/login")
    public String loginLink(Model model) {
        return "contact";
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
}