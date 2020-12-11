package ca.ulaval.glo4003.accesspasses.domain.exceptions;

public class NotFoundAccessPassException extends AccessPassException {
  private static final String ERROR = "Access pass not found";
  private static final String DESCRIPTION = "Access pass was not found";

  public NotFoundAccessPassException() {
    super(ERROR, DESCRIPTION);
  }
}
