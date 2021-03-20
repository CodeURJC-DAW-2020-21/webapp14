package daw.urjc.ayuntamiento.controller;


import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private EventService service;

    @Autowired
    private UserService userService;



    @GetMapping("/event/{id}")
    public String giveEvent(Model model, @PathVariable long id){
        Optional<Event> event = service.findId(id);

        if (event.isPresent()){
            model.addAttribute("event",event.get());
            return "mainEvent";
        }
        return "events";
    }

    @GetMapping("/event/{id}/image")
    public ResponseEntity<Object> giveImageEvent(@PathVariable long id) throws SQLException {
        Optional<Event> event = service.findId(id);
        if(event.isPresent() && event.get().getImageFile() != null){
            Resource file = new InputStreamResource(event.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(event.get().getImageFile().length()).body(file);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/removeEvent/{id}")
    public String removeEvent(@PathVariable long id) {

        Optional<Event> event = service.findId(id);
        if (event.isPresent()) {
            service.delete(id);
        }
        return "eventDelete";
    }

    @RequestMapping("/subscribe/{id}")
    public String subscribeEvent(@PathVariable long id, Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());
        if (user.get().getEventSuscribe().contains(id)){
            return "index";
        }
        user.get().getEventSuscribe().add(id);
        Optional<Event> event = service.findId(id);
        String aux1 = event.get().getTag1();
        Map<String,Integer> mapaux = user.get().getMap();
        Integer actualvalor1 = mapaux.get(aux1);
        actualvalor1 = actualvalor1+1;
        mapaux.put(aux1,actualvalor1);
        user.get().setMap(mapaux);
        user.get().getEvents().add(event.get().getName());
        userService.save(user.get());
        return "redirect:/";
    }
}
