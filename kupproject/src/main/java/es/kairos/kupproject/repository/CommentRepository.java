package es.kairos.kupproject.repository;

import es.kairos.kupproject.model.Comment;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

  List<Comment> findAllByNickname(String nickname);

  Page<Comment> findAll(Pageable page);
}
