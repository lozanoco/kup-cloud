package es.kairos.kupproject.controller.impl;

import com.fasterxml.jackson.annotation.JsonView;
import es.kairos.kupproject.controller.CommentController;
import es.kairos.kupproject.controller.impl.EntryControllerImpl.DetailedEntry;
import es.kairos.kupproject.dto.CommentDTO;
import es.kairos.kupproject.dto.EntryDTO;
import es.kairos.kupproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentControllerImpl implements CommentController {

  interface DetailedComment extends CommentDTO.Detailed, EntryDTO.Basic{}

  @Autowired
  private CommentService commentService;

  @JsonView(DetailedEntry.class)
  @PostMapping
  public ResponseEntity createComment(@RequestBody CommentDTO comment) {
    return new ResponseEntity(commentService.create(comment), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity getAllComments(Pageable page) {
    return new ResponseEntity(commentService.readAll(page), HttpStatus.OK);
  }

  @JsonView(DetailedComment.class)
  @GetMapping("/{nickname}")
  public ResponseEntity getComment(@PathVariable Long id) {
    return new ResponseEntity(commentService.read(id), HttpStatus.OK);
  }

  @JsonView(DetailedComment.class)
  @DeleteMapping("/{id}")
  public ResponseEntity deleteComment(@PathVariable CommentDTO comment) {
    commentService.delete(comment);
    return new ResponseEntity(HttpStatus.OK);
  }

  @JsonView(DetailedComment.class)
  @PutMapping
  public ResponseEntity putComment(@RequestBody CommentDTO comment) {
    return new ResponseEntity(commentService.update(comment), HttpStatus.OK);
  }
}
