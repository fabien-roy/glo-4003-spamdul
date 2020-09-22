package ca.ulaval.glo4003.domain.user.exception;

public class InvalidSexAttributeException extends InvalidUserException {
  private static final String error = "Invalid sex";
  private static final String description = "Sex should be m, f or x";

  public InvalidSexAttributeException() {
    super(error, description);
  }
}
