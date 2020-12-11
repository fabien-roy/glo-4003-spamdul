package ca.ulaval.glo4003.times.domain.exceptions;

public class InvalidDateException extends TimeException {
  private static final String ERROR = "Invalid date";
  private static final String DESCRIPTION = "Date must be of format 'dd-MM-yyyy'";

  public InvalidDateException() {
    super(ERROR, DESCRIPTION);
  }
}
