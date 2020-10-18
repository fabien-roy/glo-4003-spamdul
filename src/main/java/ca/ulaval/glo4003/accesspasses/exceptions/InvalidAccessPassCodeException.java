package ca.ulaval.glo4003.accesspasses.exceptions;

public class InvalidAccessPassCodeException extends AccessPassException {
  private static final String ERROR = "Invalid access pass code";
  private static final String DESCRIPTION = "Access pass code is invalid";

  public InvalidAccessPassCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
