package daw.urjc.ayuntamiento.service;

import daw.urjc.ayuntamiento.modules.Comment;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.repository.CommentRepository;
import daw.urjc.ayuntamiento.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;


    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public List<Comment> findAll() {
        return repository.findAll();
    }

    public Optional<Comment> findId(long id){
        return repository.findById(id);
    }

    public void save(Comment comment) {
        repository.save(comment);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Optional<Comment> findImage(Blob image){
        return repository.findByimageFile(image);
    }

    public Optional<Comment> findName(String name){
        return repository.findByName(name);
    }

    public long count(){return repository.count();}
}
