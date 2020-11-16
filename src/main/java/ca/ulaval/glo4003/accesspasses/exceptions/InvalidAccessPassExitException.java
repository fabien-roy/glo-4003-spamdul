package ca.ulaval.glo4003.accesspasses.exceptions;

public class InvalidAccessPassExitException extends AccessPassException {
  private static final String ERROR = "Impossible to exit campus";
  private static final String DESCRIPTION =
      "This accessPass was not admitted on campus and so, cannot exit";

  public InvalidAccessPassExitException() {
    super(ERROR, DESCRIPTION);
  }
}
