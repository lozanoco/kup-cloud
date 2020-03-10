package es.kairos.kupproject.service;

import es.kairos.kupproject.dto.EntryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntryService {

  EntryDTO create(EntryDTO entryDTO);

  EntryDTO read(long id);

  Page<EntryDTO> readAll(Pageable page);

  EntryDTO update(EntryDTO entryDTO);

  void delete(EntryDTO entryDTO);


}
