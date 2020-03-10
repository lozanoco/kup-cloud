package es.kairos.kupproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class User {

  @Id
  private long id;
  private String name;
  private String email;

}
