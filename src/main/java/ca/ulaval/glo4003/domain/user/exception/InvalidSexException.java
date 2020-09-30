package ca.ulaval.glo4003.domain.user.exception;

public class InvalidSexException extends UserException {
  private static final String ERROR = "Invalid sex";
  private static final String DESCRIPTION = "Sex should be m, f or x";

  public InvalidSexException() {
    super(ERROR, DESCRIPTION);
  }
}
