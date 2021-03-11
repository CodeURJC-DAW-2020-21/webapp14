package daw.urjc.ayuntamiento.repository;

import daw.urjc.ayuntamiento.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
