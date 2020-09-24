package ca.ulaval.glo4003.domain.user.exception;

public class InvalidNameException extends InvalidUserException {
  private static final String NAME = "Invalid name";
  private static final String DESCRIPTION = "Name can't be empty";

  public InvalidNameException() {
    super(NAME, DESCRIPTION);
  }
}
