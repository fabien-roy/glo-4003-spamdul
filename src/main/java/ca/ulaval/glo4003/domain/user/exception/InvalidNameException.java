package ca.ulaval.glo4003.domain.user.exception;

public class InvalidNameException extends InvalidUserException {
  private static final String error = "Invalid name";
  private static final String description = "Name can't be empty";

  public InvalidNameException() {
    super(error, description);
  }
}
