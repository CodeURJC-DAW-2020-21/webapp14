package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.repository.EventRepository;
import daw.urjc.ayuntamiento.repository.StoreRepository;
import daw.urjc.ayuntamiento.service.EventService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FormController {

    @Autowired
    private EventRepository repositoryEvent;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private EventService eventService;

    @RequestMapping("/FormEvent")
    public String formularioEventLink(Model model){
        return "formularioEventos";
    }

    @RequestMapping("/FormLocal")
    public String formularioLocalLink(Model model){
        return "formularioLocal";
    }

    @PostMapping("/events")
    public String eventcreation(Event event, MultipartFile imageField, Model model) throws IOException {

        if (!imageField.isEmpty()) {
            event.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));

        }

        // Event event = new Event(name,activities,description,date,place,reward,people,price,imageFile);
        repositoryEvent.save(event);
        model.addAttribute("event",eventService.findAll());

        return "properties";
    }

    @PostMapping("/createLocal")
    public String localcreation(Store store,MultipartFile imageField1, MultipartFile imageField2,Model model) throws IOException {

        if (!imageField1.isEmpty()) {
            store.setImageFile1(BlobProxy.generateProxy(imageField1.getInputStream(), imageField1.getSize()));
        }

        if (!imageField2.isEmpty()) {
            store.setImageFile2(BlobProxy.generateProxy(imageField2.getInputStream(), imageField2.getSize()));
        }

        storeRepository.save(store);
        model.addAttribute("store",store);
        return "store";
    }
}