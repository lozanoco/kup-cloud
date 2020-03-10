package es.kairos.kupproject.service.impl;

import es.kairos.kupproject.dto.CommentDTO;
import es.kairos.kupproject.dto.EntryDTO;
import es.kairos.kupproject.exceptions.CommentNotFoundException;
import es.kairos.kupproject.exceptions.EntryNotFoundException;
import es.kairos.kupproject.model.Comment;
import es.kairos.kupproject.model.Entry;
import es.kairos.kupproject.repository.CommentRepository;
import es.kairos.kupproject.repository.EntryRepository;
import es.kairos.kupproject.service.CommentService;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.transaction.Transactional;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private EntryRepository entryRepository;

  @Autowired
  private MapperFacade mapper;

  @Override
  public EntryDTO create(CommentDTO commentDTO) {
    Entry entry = entryRepository.findById(commentDTO.getEntry().getId())
        .orElseThrow(EntryNotFoundException::new);
    Comment commentToCreate = mapper.map(commentDTO, Comment.class);

    entry.getComments().add(commentToCreate);
    entryRepository.save(entry);
    return mapper.map(entry, EntryDTO.class);
  }

  @Override
  public CommentDTO read(long id) {
    Comment comment = commentRepository.findById(id)
        .orElseThrow(CommentNotFoundException::new);
    return mapper.map(comment, CommentDTO.class);
  }

  @Override
  public Page<CommentDTO> readAll(Pageable page) {
    return commentRepository.findAll(page)
        .map(comment -> mapper.map(comment, CommentDTO.class));
  }

  @Override
  public CommentDTO update(CommentDTO commentDTO) {
    Comment dbComment = commentRepository.findById(commentDTO.getId())
        .orElseThrow(CommentNotFoundException::new);

    Comment commentToUpdate = dbComment.builder()
        .nickname(commentDTO.getNickname())
        .content(commentDTO.getContent())
        .entry(mapper.map(commentDTO.getEntry(), Entry.class))
        .date(commentDTO.getDate())
        .build();

    return mapper.map(commentRepository.save(commentToUpdate), CommentDTO.class);
  }

  @Override
  public void delete(CommentDTO commentDTO) {
    Comment dbComment = commentRepository.findById(commentDTO.getId())
        .orElseThrow(CommentNotFoundException::new);

    commentRepository.delete(dbComment);
  }
}
