package ca.ulaval.glo4003.domain.time.exception;

public class InvalidTimeOfDayException extends TimeException {
  private static final String ERROR = "Invalid time of day";
  private static final String DESCRIPTION = "Invalid time of day";

  public InvalidTimeOfDayException() {
    super(ERROR, DESCRIPTION);
  }
}
