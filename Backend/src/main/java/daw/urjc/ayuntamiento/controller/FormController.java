package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.Comment;
import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.repository.EventRepository;
import daw.urjc.ayuntamiento.repository.StoreRepository;
import daw.urjc.ayuntamiento.service.CommentService;
import daw.urjc.ayuntamiento.service.EventService;
import daw.urjc.ayuntamiento.service.LocalService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FormController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LocalService localService;

    @RequestMapping("/FormEvent")
    public String formularioEventLink(Model model){
        return "formEvent";
    }

    @RequestMapping("/FormLocal")
    public String formularioLocalLink(Model model){
        return "formLocal";
    }

    @PostMapping("/events")
    public String eventcreation(Event event, MultipartFile imageField, Model model) throws IOException {

        if (!imageField.isEmpty()) {
            event.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
        }

        // Event event = new Event(name,activities,description,date,place,reward,people,price,imageFile);
        eventService.save(event);
        model.addAttribute("event",eventService.findAll());

        return "events";
    }

    @PostMapping("/locals")
    public String localcreation(Store store,MultipartFile image1, MultipartFile image2,Model model) throws IOException {

        if (!image1.isEmpty()) {
            store.setImageField1(BlobProxy.generateProxy(image1.getInputStream(), image1.getSize()));
        }

        if (!image2.isEmpty()) {
            store.setImageField2(BlobProxy.generateProxy(image2.getInputStream(), image2.getSize()));
        }

        localService.save(store);
        model.addAttribute("local",localService.findAll());
        return "locals";
    }

}
