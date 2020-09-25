package ca.ulaval.glo4003.domain.time.exception;

public class InvalidDayException extends RuntimeException {
  public InvalidDayException() {
    super("Invalid day");
  }
}
