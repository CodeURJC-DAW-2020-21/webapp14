package daw.urjc.ayuntamiento.Repository;

import daw.urjc.ayuntamiento.modules.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Long> {
}
