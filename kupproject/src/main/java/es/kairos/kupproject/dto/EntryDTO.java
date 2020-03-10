package es.kairos.kupproject.dto;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EntryDTO {

  public interface Detailed extends Basic {
  }

  public interface Basic {
  }

  @JsonView(Basic.class)
  private Long id;
  @JsonView(Basic.class)
  private String name;
  @JsonView(Basic.class)
  private String nickname;
  @JsonView(Basic.class)
  private String title;
  @JsonView(Basic.class)
  private String resume;
  @JsonView(Basic.class)
  private String text;
  @JsonView(Detailed.class)
  private List<CommentDTO> comments;

}
