package ca.ulaval.glo4003.users.domain.exceptions;

public class InvalidNameException extends UserException {
  private static final String NAME = "Invalid name";
  private static final String DESCRIPTION = "Name can't be empty";

  public InvalidNameException() {
    super(NAME, DESCRIPTION);
  }
}
