package es.kairos.kupproject.mappers;

import es.kairos.kupproject.dto.CommentDTO;
import es.kairos.kupproject.dto.EntryDTO;
import es.kairos.kupproject.model.Comment;
import es.kairos.kupproject.model.Entry;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class EntryToEntryDTOConverter extends BidirectionalConverter<Entry, EntryDTO> {

    @Override
    public EntryDTO convertTo(Entry entry, Type<EntryDTO> type, MappingContext mappingContext) {
        return EntryDTO.builder()
            .id(entry.getId())
            .name(entry.getName())
            .nickname(entry.getNickname())
            .resume(entry.getResume())
            .text(entry.getText())
            .title(entry.getTitle())
            .comments(entry.getComments().stream()
                .map(comment -> CommentDTO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .date(comment.getDate())
                    .nickname(comment.getNickname())
                    .build())
                .collect(Collectors.toList()))
            .build();
    }

    @Override
    public Entry convertFrom(EntryDTO entryDTO, Type<Entry> type, MappingContext mappingContext) {
        return Entry.builder()
            .id(entryDTO.getId())
            .name(entryDTO.getName())
            .nickname(entryDTO.getNickname())
            .resume(entryDTO.getResume())
            .text(entryDTO.getText())
            .title(entryDTO.getTitle())
            .comments(entryDTO.getComments().stream()
                .map(comment -> Comment.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .date(comment.getDate())
                    .nickname(comment.getNickname())
                    .build())
                .collect(Collectors.toList()))
            .build();
    }
}
