package es.kairos.kupproject.exceptions;

public class UserNotFoundException extends RuntimeException {

  public static final String DEFAULT_MESSAGE = "User not found";

  public UserNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
