package ca.ulaval.glo4003.domain.time.exception;

public class InvalidDateException extends TimeException {
  private static final String ERROR = "Invalid date";
  private static final String DESCRIPTION = "Invalid date";

  public InvalidDateException() {
    super(ERROR, DESCRIPTION);
  }
}
