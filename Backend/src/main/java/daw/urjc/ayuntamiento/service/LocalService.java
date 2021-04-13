package daw.urjc.ayuntamiento.service;

import daw.urjc.ayuntamiento.modules.Comment;
import daw.urjc.ayuntamiento.modules.Store;
import daw.urjc.ayuntamiento.repository.StoreRepository;
import daw.urjc.ayuntamiento.utils.ImageSetter;
import daw.urjc.ayuntamiento.utils.ImageUpdater;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalService {
    @Autowired
    private StoreRepository repository;


    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public List<Store> findAll() {
        return repository.findAll();
    }

    public Optional<Store> findId(long id){
        return repository.findById(id);
    }

    public void save(Store store) {
        repository.save(store);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Page<Store> findLocals(Pageable pageable) {
        return repository.findAll(PageRequest.of(pageable.getPageNumber(), 12));
    }

    public long count(){return repository.count();}


}
