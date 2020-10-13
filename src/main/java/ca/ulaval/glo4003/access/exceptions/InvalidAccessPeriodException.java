package ca.ulaval.glo4003.access.exceptions;

public class InvalidAccessPeriodException extends AccessException {
  private static final String ERROR = "Invalid access period";
  private static final String DESCRIPTION = "This period is not valid for an access pass";

  public InvalidAccessPeriodException() {
    super(ERROR, DESCRIPTION);
  }
}
