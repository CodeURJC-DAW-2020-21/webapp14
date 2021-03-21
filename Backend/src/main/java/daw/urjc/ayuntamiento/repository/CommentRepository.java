package daw.urjc.ayuntamiento.repository;

import daw.urjc.ayuntamiento.modules.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Blob;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByName(String name);

    Optional<Comment> findByimageFile(Blob imageFile);
}
