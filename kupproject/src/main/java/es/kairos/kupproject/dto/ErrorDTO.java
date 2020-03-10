package es.kairos.kupproject.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorDTO {

  private int status;

  private String message;

  private UUID code;

}
