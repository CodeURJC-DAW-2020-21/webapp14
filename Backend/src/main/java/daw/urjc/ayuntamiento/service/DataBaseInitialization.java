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


        User user1=new User("AntonioCuad","AntonioCuad@mail.com","Soy un ciudadano de Ciempozuelos bastante activo en los eventos y actividades que se organizan en el pueblo","53435243T",passwordEncoder.encode("password"));
        setUserImage(user1,"/static/images/A.png");
        List<String> roles1 = new LinkedList<>();
        roles1.add("USER");
        user1.setRoles(roles1);
        userRepository.save(user1);


        User user2=new User("admin","admin@administrador.com","Administrador","Administrador",passwordEncoder.encode("password"));
        setUserImage(user2,"/static/images/A.png");
        List<String> roles2 = new LinkedList<>();
        roles2.add("USER");
        roles2.add("ADMIN");
        user2.setRoles(roles2);
        userRepository.save(user2);


        Comment c1=new Comment("Muy recomendado");
        c1.setDate(date);
        c1.setName("Pedro Díaz");
        setCommentImage(c1,"/static/images/letter-p.png");

        Comment c2=new Comment("Genial");
        c2.setDate(date);
        c2.setName("Jesús Rodríguez");
        setCommentImage(c2,"/static/images/letter-j.jpg");

        commentRepository.save(c1);
        commentRepository.save(c2);

        Comment com1 = new Comment("Recomendado");
        com1.setDate(date);
        com1.setName("Javier Diaz");
        setCommentImage(com1,"/static/images/letter-j.jpg");

        Comment com2 = new Comment("Esta muy bien");
        com2.setDate(date);
        com2.setName("Israel Pozuelo");
        setCommentImage(com2,"/static/images/letter-i.jpg");

        Comment com3 = new Comment("Lo pasas en grande");
        com3.setDate(date);
        com3.setName("Elías Pérez");
        setCommentImage(com3, "/static/images/letter-e.png");

        commentRepository.save(com1);
        commentRepository.save(com2);
        commentRepository.save(com3);



        Event event1 = new Event("Mercadillo medieval Ciempozuelos","Espectaculo de fuego, bailes medievales, cuentacuentos...","Ven a disfrutar de nuestro popular fin de semana de mercadillo medieval en Ciempozuelos, con espectáculos y eventos para todos los públicos. Pásalo en grande en estos días tan especiales y date un viaje al pasado. Te esperamos!","18/06/2021","Plaza de la Constitucion","0","1000","0","Cultura");
        setEventImage(event1, "/static/images/Mercadillo1.jpg");
        event1.getComment().add(com1);
        event1.getComment().add(com2);
        eventRepository.save(event1);

        Event event2 = new Event("Primeras jornadas de participación ciudadana","Impulsar el conocimiento, mesas redondas...","El Ayuntamiento de Ciempozuelos organiza este fin de semana en la sala multifuncional (Avda. de Belén) las primeras jornadas abiertas de participación ciudadana.","1/3/2021","Sala multifuncional (Avda. de Belén)","0","2000","","Cultura");
        setEventImage(event2,"static/images/evento1.png");
        event2.getComment().add(c1);
        event2.getComment().add(c2);
        eventRepository.save(event2);

        Event event3 = new Event("Taller educativo para niños","Creacion de pequeños robots, manualidades de madera, cuentacuentos...","Como cada año llega la hora del taller para los niños. Este año traemos mas actividades con la gran incorporacion de un evento para crear pequeños robots. ","28/05/2021","Colegio Público Virgen del Consuelo","0","200","0","Cultura");
        setEventImage(event3,"static/images/Taller niños.jpg");
        eventRepository.save(event3);

        Event event4 = new Event("Torneo de Baloncesto 3x3","Concurso de triples, concurso de mates...","Si eres fan del baloncesto no dudes en apuntarte con tus amigos al proximo torneo 3x3 para pasar un buen rato jugando partidos y disfrutando de otras actividades aparte de los partidos.","11/05/2021","Polideportivo Municipal","100","48","20","Deporte");
        setEventImage(event4,"static/images/Baloncesto.jpg");
        eventRepository.save(event4);


        Event event5 = new Event("Torneo Ajedrez","Clasificatorias, final, entrega de premios...","Ven a poner a prueba tus capacidades, nunca un torneo había sido tan emocionante. Diviertete en este torneo dedicado a los amantes del ajedrez. El torneo se celebrará en la biblioteca municipal.","30/03/2021","Biblioteca Municipal","30","32","5","Deporte");
        setEventImage(event5,"static/images/ajedrez.jpeg");
        eventRepository.save(event5);

        Event event6 = new Event("Mercadillo medieval Ciempozuelos","Espectaculo de fuego, bailes medievales, cuentacuentos...","Ven a disfrutar de nuestro popular fin de semana de mercadillo medieval en Ciempozuelos, con espectáculos y eventos para todos los públicos. Pásalo en grande en estos días tan especiales y date un viaje al pasado. Te esperamos!","18/06/2021","Plaza de la Constitucion","0","1000","0","Cultura");
        setEventImage(event6, "/static/images/Mercadillo1.jpg");
        eventRepository.save(event6);

        Event event7 = new Event("Primeras jornadas de participación ciudadana","Impulsar el conocimiento, mesas redondas...","El Ayuntamiento de Ciempozuelos organiza este fin de semana en la sala multifuncional (Avda. de Belén) las primeras jornadas abiertas de participación ciudadana.","1/3/2021","Sala multifuncional (Avda. de Belén)","0","2000","","Cultura");
        setEventImage(event7,"static/images/evento1.png");
        eventRepository.save(event7);

        Event event8 = new Event("Taller educativo para niños","Creacion de pequeños robots, manualidades de madera, cuentacuentos...","Como cada año llega la hora del taller para los niños. Este año traemos mas actividades con la gran incorporacion de un evento para crear pequeños robots.","28/05/2021","Colegio Público Virgen del Consuelo","0","200","0","Cultura");
        setEventImage(event8,"static/images/Taller niños.jpg");
        eventRepository.save(event8);

        Event event9 = new Event("Torneo de Baloncesto 3x3","Concurso de triples, concurso de mates...","Si eres fan del baloncesto no dudes en apuntarte con tus amigos al proximo torneo 3x3 para pasar un buen rato jugando partidos y disfrutando de otras actividades aparte de los partidos.","11/05/2021","Polideportivo Municipal","100","48","20","Deporte");
        setEventImage(event9,"static/images/Baloncesto.jpg");
        eventRepository.save(event9);

        Event event10 = new Event("Torneo Ajedrez","Clasificatorias, final, entrega de premios...","Ven a poner a prueba tus capacidades, nunca un torneo había sido tan emocionante. Diviertete en este torneo dedicado a los amantes del ajedrez. El torneo se celebrará en la biblioteca municipal.","30/03/2021","Biblioteca Municipal","30","32","5","Deporte");
        setEventImage(event10,"static/images/ajedrez.jpeg");
        eventRepository.save(event10);



        Comment c3=new Comment("Muy buen ambiente");
        c3.setDate(date);
        c3.setName("Pepe Vázquez");
        setCommentImage(c3,"static/images/letter-p.png");
        Comment c4=new Comment("Siempre es un acierto acudir");
        c4.setDate(date);
        c4.setName("Manuel Rodrigo");
        setCommentImage(c4,"static/images/letter-m.jpg");
        Comment c5=new Comment("Te tratan muy bien y buenos precios");
        c5.setDate(date);
        c5.setName("Gabriel Vera");
        setCommentImage(c5,"static/images/letter-g.jpg");

        commentRepository.save(c3);
        commentRepository.save(c4);
        commentRepository.save(c5);


        Store store1 = new Store("Galway Irish Tavern","Te esperamos para que disfrutes con tu familia y amigos del mejor ambiente, junto a una buena cerveza irlandesa y nuestros espectáculos de música en directo","Tu taverna irlandesa en Ciempozuelos","Refrescos, música en directo, buen ambiente...","Martes","Lunes","9:00","00:00","Plaza de Ventura Rodríguez, 4, 28350 Ciempozuelos, Madrid","40.1588847","-3.6174809");
        setStoreImage1(store1,"/static/images/Galway.png");
        setStoreImage2(store1,"/static/images/Galway1.jpg");
        store1.getComment().add(c3);
        store1.getComment().add(c4);
        store1.getComment().add(c5);
        storeRepository.save(store1);

        Store store2 = new Store("Taberna El Camachito","Disfruta de nuestra amplia carta con raciones variadas, junto a una buena cerveza o refresco en un ambiente ideal. Te esperamos!","Tu local ideal en Ciempozuelos para comer y beber en la mejor compañía","Disponemos de una amplia carta con variadas raciones y bebidas. No dudes en pedirnosla","Lunes","Domingo","10:00","23:30","Calle España, 5, 28350 Ciempozuelos, Madrid","40.1591","-3.621");
        setStoreImage1(store2,"/static/images/Camachito1.jpg");
        setStoreImage2(store2, "/static/images/Camachito2.jpg");
        store1.getComment().add(com1);
        store1.getComment().add(com2);
        storeRepository.save(store2);

        Store store3 = new Store("Ciempizzas","Ven a probar nuestras riquísimas pizzas, con toda variedad de ingredientes y complementos, al mejor precio, incluso si lo prefieres, te lo enviamos a domicilio. Tú eliges!","Pizzería en Ciempozuelos con servicio a domicilio","Tenemos una enorme variedad de ingredientes para que eligas los que más te gusten para tu pizza, junto con toda clase de refrescos y complementos.","Martes","Domingo","13:00","22:30","Calle de San Francisco, 2, 28350 Ciempozuelos, Madrid","40.1535574","-3.6221331");
        setStoreImage1(store3,"/static/images/pizza.png");
        setStoreImage2(store3, "/static/images/ciempi2.jpg");
        storeRepository.save(store3);

        Store store4 = new Store("Galway Irish Tavern","Te esperamos para que disfrutes con tu familia y amigos del mejor ambiente, junto a una buena cerveza irlandesa y nuestros espectáculos de música en directo","Tu taverna irlandesa en Ciempozuelos","Refrescos, música en directo, buen ambiente...","Martes","Lunes","9:00","00:00","Plaza de Ventura Rodríguez, 4, 28350 Ciempozuelos, Madrid","40.1588847","-3.6174809");
        setStoreImage1(store4,"/static/images/Galway.png");
        setStoreImage2(store4,"/static/images/Galway1.jpg");
        storeRepository.save(store4);

        Store store5 = new Store("Taberna El Camachito","Disfruta de nuestra amplia carta con raciones variadas, junto a una buena cerveza o refresco en un ambiente ideal. Te esperamos!","Tu local ideal en Ciempozuelos para comer y beber en la mejor compañía","Disponemos de una amplia carta con variadas raciones y bebidas. No dudes en pedirnosla","Lunes","Domingo","10:00","23:30","Calle España, 5, 28350 Ciempozuelos, Madrid","40.1591","-3.621");
        setStoreImage1(store5,"/static/images/Camachito1.jpg");
        setStoreImage2(store5, "/static/images/Camachito2.jpg");
        storeRepository.save(store5);

        Store store6 = new Store("Ciempizzas","Ven a probar nuestras riquísimas pizzas, con toda variedad de ingredientes y complementos, al mejor precio, incluso si lo prefieres, te lo enviamos a domicilio. Tú eliges!","Pizzería en Ciempozuelos con servicio a domicilio","Tenemos una enorme variedad de ingredientes para que eligas los que más te gusten para tu pizza, junto con toda clase de refrescos y complementos.","Martes","Domingo","13:00","22:30","Calle de San Francisco, 2, 28350 Ciempozuelos, Madrid","40.1535574","-3.6221331");
        setStoreImage1(store6,"/static/images/pizza.png");
        setStoreImage2(store6, "/static/images/ciempi2.jpg");
        storeRepository.save(store6);

        Store store7 = new Store("Galway Irish Tavern","Te esperamos para que disfrutes con tu familia y amigos del mejor ambiente, junto a una buena cerveza irlandesa y nuestros espectáculos de música en directo","Tu taverna irlandesa en Ciempozuelos","Refrescos, música en directo, buen ambiente...","Martes","Lunes","9:00","00:00","Plaza de Ventura Rodríguez, 4, 28350 Ciempozuelos, Madrid","40.1588847","-3.6174809");
        setStoreImage1(store7,"/static/images/Galway.png");
        setStoreImage2(store7,"/static/images/Galway1.jpg");
        storeRepository.save(store7);

        Store store8 = new Store("Taberna El Camachito","Disfruta de nuestra amplia carta con raciones variadas, junto a una buena cerveza o refresco en un ambiente ideal. Te esperamos!","Tu local ideal en Ciempozuelos para comer y beber en la mejor compañía","Disponemos de una amplia carta con variadas raciones y bebidas. No dudes en pedirnosla","Lunes","Domingo","10:00","23:30","Calle España, 5, 28350 Ciempozuelos, Madrid","40.1591","-3.621");
        setStoreImage1(store8,"/static/images/Camachito1.jpg");
        setStoreImage2(store8, "/static/images/Camachito2.jpg");
        storeRepository.save(store8);

        Store store9 = new Store("Ciempizzas","Ven a probar nuestras riquísimas pizzas, con toda variedad de ingredientes y complementos, al mejor precio, incluso si lo prefieres, te lo enviamos a domicilio. Tú eliges!","Pizzería en Ciempozuelos con servicio a domicilio","Tenemos una enorme variedad de ingredientes para que eligas los que más te gusten para tu pizza, junto con toda clase de refrescos y complementos.","Martes","Domingo","13:00","22:30","Calle de San Francisco, 2, 28350 Ciempozuelos, Madrid","40.1535574","-3.6221331");
        setStoreImage1(store9,"/static/images/pizza.png");
        setStoreImage2(store9, "/static/images/ciempi2.jpg");
        storeRepository.save(store9);

        Store store10 = new Store("Taberna El Camachito","Disfruta de nuestra amplia carta con raciones variadas, junto a una buena cerveza o refresco en un ambiente ideal. Te esperamos!","Tu local ideal en Ciempozuelos para comer y beber en la mejor compañía","Disponemos de una amplia carta con variadas raciones y bebidas. No dudes en pedirnosla","Lunes","Domingo","10:00","23:30","Calle España, 5, 28350 Ciempozuelos, Madrid","40.1591","-3.621");
        setStoreImage1(store10,"/static/images/Camachito1.jpg");
        setStoreImage2(store10, "/static/images/Camachito2.jpg");
        storeRepository.save(store10);

        Store store11 = new Store("Galway Irish Tavern","Te esperamos para que disfrutes con tu familia y amigos del mejor ambiente, junto a una buena cerveza irlandesa y nuestros espectáculos de música en directo","Tu taverna irlandesa en Ciempozuelos","Refrescos, música en directo, buen ambiente...","Martes","Lunes","9:00","00:00","Plaza de Ventura Rodríguez, 4, 28350 Ciempozuelos, Madrid","40.1588847","-3.6174809");
        setStoreImage1(store11,"/static/images/Galway.png");
        setStoreImage2(store11,"/static/images/Galway1.jpg");
        storeRepository.save(store11);

        Store store12 = new Store("Ciempizzas","Ven a probar nuestras riquísimas pizzas, con toda variedad de ingredientes y complementos, al mejor precio, incluso si lo prefieres, te lo enviamos a domicilio. Tú eliges!","Pizzería en Ciempozuelos con servicio a domicilio","Tenemos una enorme variedad de ingredientes para que eligas los que más te gusten para tu pizza, junto con toda clase de refrescos y complementos.","Martes","Domingo","13:00","22:30","Calle de San Francisco, 2, 28350 Ciempozuelos, Madrid","40.1535574","-3.6221331");
        setStoreImage1(store12,"/static/images/pizza.png");
        setStoreImage2(store12, "/static/images/ciempi2.jpg");
        storeRepository.save(store12);

        Store store13 = new Store("Taberna El Camachito","Disfruta de nuestra amplia carta con raciones variadas, junto a una buena cerveza o refresco en un ambiente ideal. Te esperamos!","Tu local ideal en Ciempozuelos para comer y beber en la mejor compañía","Disponemos de una amplia carta con variadas raciones y bebidas. No dudes en pedirnosla","Lunes","Domingo","10:00","23:30","Calle España, 5, 28350 Ciempozuelos, Madrid","40.1591","-3.621");
        setStoreImage1(store13,"/static/images/Camachito1.jpg");
        setStoreImage2(store13, "/static/images/Camachito2.jpg");
        storeRepository.save(store13);
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
}