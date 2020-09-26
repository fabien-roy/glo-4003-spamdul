package ca.ulaval.glo4003.domain.time.exception;

public class InvalidDayException extends TimeException {
  private static final String ERROR = "Invalid day";
  private static final String DESCRIPTION = "Invalid day";

  public InvalidDayException() {
    super(ERROR, DESCRIPTION);
  }
}
