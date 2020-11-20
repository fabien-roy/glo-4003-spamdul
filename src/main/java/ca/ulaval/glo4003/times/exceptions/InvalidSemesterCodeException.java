package ca.ulaval.glo4003.times.exceptions;

public class InvalidSemesterCodeException extends TimeException {
  private static final String ERROR = "Invalid semester code";
  private static final String DESCRIPTION = "Semester code is invalid";

  public InvalidSemesterCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
