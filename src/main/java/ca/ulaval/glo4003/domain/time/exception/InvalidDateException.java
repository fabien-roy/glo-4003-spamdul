package ca.ulaval.glo4003.domain.time.exception;

public class InvalidDateException extends RuntimeException {
  public InvalidDateException() {
    super("Invalid date");
  }
}
