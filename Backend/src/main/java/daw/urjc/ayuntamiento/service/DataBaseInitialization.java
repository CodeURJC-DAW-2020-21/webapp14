package daw.urjc.ayuntamiento.service;

import daw.urjc.ayuntamiento.modules.Comment;
import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.repository.CommentRepository;
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
    private CommentRepository commentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Date date = new Date();

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


        Comment c1=new Comment("Hola buenos dias me llamo javi");
        c1.setDate(date);
        c1.setName("Antoñito");
        setCommentImage(c1,"/static/images/alexelcapo.jpg");

        Comment c2=new Comment("El pozos");
        c2.setDate(date);
        c2.setName("Cocas");
        setCommentImage(c2,"/static/images/pozo.jpg");

        commentRepository.save(c1);
        commentRepository.save(c2);



        Event event1 = new Event("Ajedrez","jugar","juegos varios","12/12/22","mi casa","0","10","0");
        setEventImage(event1, "/static/images/pozo.jpg");
        event1.getComment().add(c1);
        event1.getComment().add(c2);

        eventRepository.save(event1);

        Event event2 = new Event("Furbo","furbo","furbo","12/12/22","furbo","1","2","3");
        setEventImage(event2,"static/images/alexelcapo.jpg");

        eventRepository.save(event2);

        Event event3 = new Event("Furbo","Furbo","furbo","12/12/22","furbo","1","2","3");
        setEventImage(event3,"static/images/alexelcapo.jpg");
        eventRepository.save(event3);

        Event event4 = new Event("Furbo","furbo","furbo","22/12/22","furbo","1","2","3");
        setEventImage(event4,"static/images/alexelcapo.jpg");
        eventRepository.save(event4);


        Event event6 = new Event("Furbo","furbo","furbo","22/12/22","furbo","1","2","3");
        setEventImage(event6,"static/images/alexelcapo.jpg");
        eventRepository.save(event6);

        Event event7 = new Event("Furbo","furbo","furbo","22/12/22","furbo","1","2","3");
        setEventImage(event7,"static/images/alexelcapo.jpg");
        eventRepository.save(event7);




        Comment c3=new Comment("pozito calvo un clabito");
        c3.setDate(date);
        Comment c4=new Comment("Lil pozoi");
        c4.setDate(date);
        Comment c5=new Comment("Xx__pozoSlayer__xX");
        c5.setDate(date);

        commentRepository.save(c3);
        commentRepository.save(c4);
        commentRepository.save(c5);


        Store store1 = new Store("Pozos.SL","Hola soy el pozos","pozeria","pozos","pozos","pozos","pozos","pozos");
        setStoreImage1(store1,"/static/images/pozo.jpg");
        setStoreImage2(store1,"/static/images/alexelcapo.jpg");

        store1.getComment().add(c3);
        store1.getComment().add(c4);
        store1.getComment().add(c5);
        storeRepository.save(store1);

        Store store2 = new Store("Pozos.SL","Hola soy el pozos","pozeria","pozos","pozos","pozos","pozos","pozos");
        setStoreImage1(store2,"/static/images/pozo.jpg");
        storeRepository.save(store2);

        Store store3 = new Store("Pozos.SL","Hola soy el pozos","pozeria","pozos","pozos","pozos","pozos","pozos");
        setStoreImage1(store3,"/static/images/pozo.jpg");
        storeRepository.save(store3);

        Store store4 = new Store("Pozos.SL","Hola soy el pozos","pozeria","pozos","pozos","pozos","pozos","pozos");
        setStoreImage1(store4,"/static/images/pozo.jpg");
        storeRepository.save(store4);

        Store store5 = new Store("Pozos.SL","Hola soy el pozos","pozeria","pozos","pozos","pozos","pozos","pozos");
        setStoreImage1(store5,"/static/images/pozo.jpg");
        storeRepository.save(store5);





    }

    private void setStoreImage1(Store store, String classpathResource) throws IOException {
        Resource image = new ClassPathResource(classpathResource);
        store.setImageField1(BlobProxy.generateProxy(image.getInputStream(),image.contentLength()));
    }
    private void setStoreImage2(Store store, String classpathResource) throws IOException {
        Resource image = new ClassPathResource(classpathResource);
        store.setImageField2(BlobProxy.generateProxy(image.getInputStream(),image.contentLength()));
    }

    public void setEventImage(Event event, String classpathResource) throws IOException{
        Resource image = new ClassPathResource(classpathResource);
        event.setImageFile(BlobProxy.generateProxy(image.getInputStream(),image.contentLength()));
    }
    public void setCommentImage(Comment comment, String classpathResource) throws IOException{
        Resource image = new ClassPathResource(classpathResource);
        comment.setImageFile(BlobProxy.generateProxy(image.getInputStream(),image.contentLength()));
    }

    public void setUserImage(User user, String classpathResource) throws IOException{
        Resource image = new ClassPathResource(classpathResource);
        user.setImageFile(BlobProxy.generateProxy(image.getInputStream(),image.contentLength()));
    }

    public void setUserRoles(User user, List<String> roles){
        user.setRoles(roles);
    }



}
