package es.kairos.kupproject.repository;

import es.kairos.kupproject.model.Entry;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Long> {

  Optional<Entry> findByName(String name);

  Page<Entry> findAll(Pageable page);
}
