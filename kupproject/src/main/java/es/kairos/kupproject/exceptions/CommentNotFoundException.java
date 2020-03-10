package es.kairos.kupproject.exceptions;

public class CommentNotFoundException extends RuntimeException {

  public static final String DEFAULT_MESSAGE = "Comment not found in database";

  public CommentNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public CommentNotFoundException(String message) {
    super(message);
  }


}
