package es.kairos.kupproject.service;

import es.kairos.kupproject.dto.CommentDTO;
import es.kairos.kupproject.dto.EntryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

  EntryDTO create(CommentDTO entryDTO);

  CommentDTO read(long id);

  Page<CommentDTO> readAll(Pageable page);

  CommentDTO update(CommentDTO entryDTO);

  void delete(CommentDTO entryDTO);


}
