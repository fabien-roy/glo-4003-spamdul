package ca.ulaval.glo4003.domain.user.exception;

public class InvalidBirthDateException extends UserException {
  private static final String ERROR = "Invalid birth date";
  private static final String DESCRIPTION = "Birth date should be in this format : dd-mm-yyyy";

  public InvalidBirthDateException() {
    super(ERROR, DESCRIPTION);
  }
}
