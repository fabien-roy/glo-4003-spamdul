package ca.ulaval.glo4003.access.exceptions;

public class NotFoundAccessPassException extends AccessException {
  private static final String ERROR = "Access pass not found";
  private static final String DESCRIPTION = "Access pass was not found";

  public NotFoundAccessPassException() {
    super(ERROR, DESCRIPTION);
  }
}
