package daw.urjc.ayuntamiento.repository;

import daw.urjc.ayuntamiento.modules.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
