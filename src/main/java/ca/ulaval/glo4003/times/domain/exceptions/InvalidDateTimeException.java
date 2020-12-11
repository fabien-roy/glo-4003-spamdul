package ca.ulaval.glo4003.times.domain.exceptions;

public class InvalidDateTimeException extends TimeException {
  private static final String ERROR = "Invalid date time";
  private static final String DESCRIPTION = "Date time must be of format 'dd-MM-yyyy HH:mm:ss'";

  public InvalidDateTimeException() {
    super(ERROR, DESCRIPTION);
  }
}
