package daw.urjc.ayuntamiento.service;

import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class DataBaseInitialization {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IOException {


        userRepository.save(new User("AntonioCuad","AntonioCuad@otaku.com","","53435243T",passwordEncoder.encode("password"), "USER" ));
        userRepository.save(new User("admin","admin@admin.com","admin","admin",passwordEncoder.encode("password"), "ADMIN","USER" ));
    }


}
