package daw.urjc.ayuntamiento.service;

import daw.urjc.ayuntamiento.modules.Event;
import daw.urjc.ayuntamiento.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;


    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public void save(Event event) {
        repository.save(event);
    }

    public Optional<Event> findId(long id){
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Page<Event> findEvents(Pageable pageable) {
        return repository.findAll(PageRequest.of(pageable.getPageNumber(), 3));
    }

    public long count(){return repository.count();}

    public List<Event> findAllByTag1(String tag1){return repository.findAllByTag1(tag1);}

}
