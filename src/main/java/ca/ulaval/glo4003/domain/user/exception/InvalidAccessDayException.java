package ca.ulaval.glo4003.domain.user.exception;

public class InvalidAccessDayException extends UserException {
  private static final String ERROR = "Invalid access day";
  private static final String DESCRIPTION = "Access day is invalid";

  public InvalidAccessDayException() {
    super(ERROR, DESCRIPTION);
  }
}
