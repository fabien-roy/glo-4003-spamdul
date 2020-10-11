package ca.ulaval.glo4003.access.exceptions;

public class AccessExceptionPeriodsException extends AccessException {
  private static final String ERROR = "Invalid access period";
  private static final String DESCRIPTION = "This period is not valid for an access pass";

  public AccessExceptionPeriodsException() {
    super(ERROR, DESCRIPTION);
  }
}
