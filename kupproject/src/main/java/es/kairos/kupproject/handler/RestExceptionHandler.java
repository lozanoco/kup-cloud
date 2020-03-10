package es.kairos.kupproject.handler;

import es.kairos.kupproject.dto.ErrorDTO;
import es.kairos.kupproject.exceptions.CommentNotFoundException;
import es.kairos.kupproject.exceptions.EntryNotFoundException;
import java.util.UUID;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private ErrorDTO buildErrorDTO(Exception ex, HttpStatus status) {
    UUID code = UUID.randomUUID();
    log.error("An error has occurred - {}", code);
    log.info("message - {}", ex.getMessage());

    return ErrorDTO.builder()
        .code(code)
        .message(ex.getMessage())
        .status(status.value())
        .build();
  }

  @ExceptionHandler(value = {EntryNotFoundException.class, CommentNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  protected ResponseEntity<ErrorDTO> handleNotFoundException(Exception ex,
      WebRequest request) {
    return buildResponseError(ex, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    return new ResponseEntity<>(buildErrorDTO(ex, HttpStatus.BAD_REQUEST), headers, status);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<ErrorDTO> constraintViolationException(Exception ex,
      WebRequest request) {
    return buildResponseError(ex, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<ErrorDTO> buildResponseError(Exception ex, HttpStatus status) {
    log.info("Message - {}", ex.getMessage());

    return new ResponseEntity<>(buildErrorDTO(ex, status), status);
  }
}
