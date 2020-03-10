package es.kairos.kupproject.exceptions;

public class EntryNotFoundException extends RuntimeException {

  public static final String DEFAULT_MESSAGE = "Entry not found";

  public EntryNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public EntryNotFoundException(String message) {
    super(message);
  }
}
