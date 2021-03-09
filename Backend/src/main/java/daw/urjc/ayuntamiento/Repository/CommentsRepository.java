package daw.urjc.ayuntamiento.Repository;

import daw.urjc.ayuntamiento.modules.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
