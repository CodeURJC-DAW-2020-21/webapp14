package daw.urjc.ayuntamiento.repository;

import daw.urjc.ayuntamiento.modules.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
