package ca.ulaval.glo4003.accesspasses.domain.exceptions;

public class InvalidAccessPassEntryException extends AccessPassException {
  private static final String ERROR = "Invalid access pass entry";
  private static final String DESCRIPTION = "This accessPass has already been admitted";

  public InvalidAccessPassEntryException() {
    super(ERROR, DESCRIPTION);
  }
}
