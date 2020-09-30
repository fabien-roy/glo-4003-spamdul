package ca.ulaval.glo4003.domain.time.exception;

public class InvalidTimeException extends TimeException {
  private static final String ERROR = "Invalid time";
  private static final String DESCRIPTION = "Invalid time";

  public InvalidTimeException() {
    super(ERROR, DESCRIPTION);
  }
}
