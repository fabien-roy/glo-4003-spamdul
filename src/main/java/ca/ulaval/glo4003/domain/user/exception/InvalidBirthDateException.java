package ca.ulaval.glo4003.domain.user.exception;

public class InvalidBirthDateException extends InvalidUserException {
  private static final String error = "Invalid birth date";
  private static final String description = "Birth date should be in this format : dd-mm-yyyy";

  public InvalidBirthDateException() {
    super(error, description);
  }
}
