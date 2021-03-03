package daw.urjc.ayuntamiento.Controller;

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
}