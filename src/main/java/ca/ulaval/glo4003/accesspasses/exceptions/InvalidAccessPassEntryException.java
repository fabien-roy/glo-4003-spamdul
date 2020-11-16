package ca.ulaval.glo4003.accesspasses.exceptions;

public class InvalidAccessPassEntryException extends AccessPassException {
  private static final String ERROR = "Unable to enter on campus";
  private static final String DESCRIPTION = "This accessPass has already been admitted";

  public InvalidAccessPassEntryException() {
    super(ERROR, DESCRIPTION);
  }
}
