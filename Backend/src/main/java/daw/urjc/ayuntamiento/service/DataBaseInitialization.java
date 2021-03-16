package daw.urjc.ayuntamiento.service;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.repository.EventRepository;
import daw.urjc.ayuntamiento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Calendar;

@Service
public class DataBaseInitialization {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IOException {

        //Calendar c1 = Calendar.getInstance();
       // c1.set(2021, Calendar.MAY, 2, 18, 30);

        userRepository.save(new User("AntonioCuad","AntonioCuad@otaku.com","","53435243T",passwordEncoder.encode("password"), "USER" ));
        userRepository.save(new User("admin","admin@admin.com","admin","admin",passwordEncoder.encode("password"), "ADMIN","USER" ));
        //eventRepository.save((new Event("Ajedrez","jugar","rgrjigir",c1.getTime(),"bibilioteca","frjfr","4","4",null)));

    }


}
