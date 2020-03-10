package es.kairos.kupproject.dto;

import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentDTO {

  public interface Detailed extends Basic {}
  public interface Basic{}

  @JsonView(Basic.class)
  private Long id;
  @JsonView(Basic.class)
  private String nickname;
  @JsonView(Basic.class)
  private String content;
  @JsonView(Basic.class)
  private LocalDate date;
  @JsonView(Detailed.class)
  private EntryDTO entry;

}
