package daw.urjc.ayuntamiento.service;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.repository.EventRepository;
import daw.urjc.ayuntamiento.repository.StoreRepository;
import daw.urjc.ayuntamiento.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class DataBaseInitialization {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() throws IOException {

        //Calendar c1 = Calendar.getInstance();
       // c1.set(2021, Calendar.MAY, 2, 18, 30);


        User user1=new User("AntonioCuad","AntonioCuad@otaku.com","","53435243T",passwordEncoder.encode("password")/*, "USER" */);
        setUserImage(user1,"/static/images/pozo.jpg");
        List<String> roles1 = new LinkedList<>();
        roles1.add("USER");
        user1.setRoles(roles1);
        userRepository.save(user1);


        User user2=new User("admin","admin@admin.com","admin","admin",passwordEncoder.encode("password")/*, "ADMIN" ,"USER" */);
        setUserImage(user2,"/static/images/pozo.jpg");
        List<String> roles2 = new LinkedList<>();
        roles2.add("USER");
        roles2.add("ADMIN");
        user2.setRoles(roles2);
        userRepository.save(user2);


        Event event1 = new Event("Ajedrez","jugar","juegos varios","12/12/22","mi casa","0","10","0");
        setEventImage(event1, "/static/images/pozo.jpg");
        eventRepository.save(event1);

        Store store1 = new Store("Pozos.SL","Hola soy el pozos","pozeria","pozos","pozos","pozos","pozos","pozos");
        setStoreImage(store1,"/static/images/pozo.jpg");
        storeRepository.save(store1);
    }

    private void setStoreImage(Store store, String classpathResource) throws IOException {
        Resource image = new ClassPathResource(classpathResource);
        store.setImageFile1(BlobProxy.generateProxy(image.getInputStream(),image.contentLength()));
    }

    public void setEventImage(Event event, String classpathResource) throws IOException{
        Resource image = new ClassPathResource(classpathResource);
        event.setImageFile(BlobProxy.generateProxy(image.getInputStream(),image.contentLength()));
    }

    public void setUserImage(User user, String classpathResource) throws IOException{
        Resource image = new ClassPathResource(classpathResource);
        user.setImageFile(BlobProxy.generateProxy(image.getInputStream(),image.contentLength()));
    }

    public void setUserRoles(User user, List<String> roles){
        user.setRoles(roles);
    }

}
