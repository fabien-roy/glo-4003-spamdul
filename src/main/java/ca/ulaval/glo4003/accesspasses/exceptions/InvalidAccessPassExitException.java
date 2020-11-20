package ca.ulaval.glo4003.accesspasses.exceptions;

public class InvalidAccessPassExitException extends AccessPassException {
  private static final String ERROR = "Invalid access pass exit";
  private static final String DESCRIPTION = "This access pass has never been admitted on campus";

  public InvalidAccessPassExitException() {
    super(ERROR, DESCRIPTION);
  }
}
