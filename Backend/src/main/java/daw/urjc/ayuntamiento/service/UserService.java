package daw.urjc.ayuntamiento.service;

import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository repository;


    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void save(User user) {
        repository.save(user);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Optional<User> findId(long id){
        return repository.findById(id);
    }

    public  Optional<User> findByName(String name){
        return repository.findByName(name);
    }
    public Optional<User> findByDNI(String DNI){
        return repository.findByDNI(DNI);
    }

    public long count(){return repository.count();}



}
