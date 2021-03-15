package daw.urjc.ayuntamiento.repository;

import daw.urjc.ayuntamiento.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findByDNI(String DNI);
}
