package es.kairos.kupproject.service.impl;

import es.kairos.kupproject.dto.CommentDTO;
import es.kairos.kupproject.dto.EntryDTO;
import es.kairos.kupproject.exceptions.EntryNotFoundException;
import es.kairos.kupproject.model.Comment;
import es.kairos.kupproject.model.Entry;
import es.kairos.kupproject.repository.EntryRepository;
import es.kairos.kupproject.service.EntryService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EntryServiceImpl implements EntryService {

  @Autowired
  private EntryRepository entryRepository;

  @Autowired
  private MapperFacade mapper;

  @Override
  public EntryDTO create(EntryDTO entryDTO) {
    // builder example --> is possible do with orika mapper
    Entry entryToSave = Entry.builder()
        .name(entryDTO.getName())
        .nickname(entryDTO.getNickname())
        .title(entryDTO.getTitle())
        .resume(entryDTO.getResume())
        .text(entryDTO.getText())
        .comments(entryDTO.getComments().stream()
            .map(c -> Comment.builder()
                .nickname(c.getNickname())
                .content(c.getContent())
                .date(c.getDate())
                .build()).collect(Collectors.toList()))
        .build();
    Entry createdEntryDTO = entryRepository.save(entryToSave);
    return mapper.map(createdEntryDTO, EntryDTO.class);
  }

  @Override
  public EntryDTO read(long id) {
    Entry entry = entryRepository.findById(id)
        .orElseThrow(EntryNotFoundException::new);
    return mapper.map(entry, EntryDTO.class);
  }

  @Override
  public Page<EntryDTO> readAll(Pageable page) {
    return entryRepository.findAll(page)
        .map(entry -> mapper.map(entry, EntryDTO.class));
  }

  @Override
  public EntryDTO update(EntryDTO entryDTO) {
    Entry entryToUpdate = entryRepository.findByName(entryDTO.getName())
        .orElseThrow(EntryNotFoundException::new);
    return mapper.map(entryToUpdate, EntryDTO.class).builder()
        .name(entryDTO.getName())
        .nickname(entryDTO.getNickname())
        .title(entryDTO.getTitle())
        .resume(entryDTO.getResume())
        .text(entryDTO.getText())
        .comments(entryDTO.getComments().stream()
            .map(c -> CommentDTO.builder()
                .nickname(c.getNickname())
                .content(c.getContent())
                .date(c.getDate())
                .build()).collect(Collectors.toList()))
        .build();
  }

  @Override
  public void delete(EntryDTO entryDTO) {
    Entry entryToDelete = entryRepository.findByName(entryDTO.getName())
        .orElseThrow(EntryNotFoundException::new);
    forceLazyChargeForCascadeWorks(entryToDelete);
    entryRepository.delete(mapper.map(entryToDelete, Entry.class));
  }

  private void forceLazyChargeForCascadeWorks(Entry entryToDelete) {
    entryToDelete.getComments().size();
  }

}
