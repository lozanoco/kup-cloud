package es.kairos.kupproject.controller.impl;

import com.fasterxml.jackson.annotation.JsonView;
import es.kairos.kupproject.controller.EntryController;
import es.kairos.kupproject.dto.CommentDTO;
import es.kairos.kupproject.dto.EntryDTO;
import es.kairos.kupproject.service.EntryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/entries")
public class EntryControllerImpl  implements EntryController {

  interface DetailedEntry extends EntryDTO.Detailed, CommentDTO.Basic{}

  @Autowired
  private EntryService entryService;

  @JsonView(DetailedEntry.class)
  @PostMapping
  public ResponseEntity<EntryDTO> createEntry(@RequestBody EntryDTO entry) {
    return new ResponseEntity(entryService.create(entry), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Page<EntryDTO>> getAllEntries(Pageable page) {
    return new ResponseEntity(entryService.readAll(page), HttpStatus.OK);
  }

  @JsonView(DetailedEntry.class)
  @GetMapping("/{id}")
  public ResponseEntity<EntryDTO> getEntry(@PathVariable Long id) {
    return new ResponseEntity(entryService.read(id), HttpStatus.OK);
  }

  @JsonView(EntryDTO.Basic.class)
  @DeleteMapping
  public ResponseEntity deleteEntry(@RequestBody EntryDTO entry) {
    entryService.delete(entry);
    return new ResponseEntity(HttpStatus.OK);
  }

  @JsonView(DetailedEntry.class)
  @PutMapping
  public ResponseEntity<EntryDTO> putEntry(@RequestBody EntryDTO entry) {
    return new ResponseEntity(entryService.update(entry), HttpStatus.OK);
  }
}
