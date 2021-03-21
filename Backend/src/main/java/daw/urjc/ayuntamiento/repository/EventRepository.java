package daw.urjc.ayuntamiento.repository;

import daw.urjc.ayuntamiento.modules.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByTag1(String tag1);
}
