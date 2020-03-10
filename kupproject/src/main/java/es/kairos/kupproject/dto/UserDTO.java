package es.kairos.kupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

  private long id;
  private String name;
  private String email;

}
