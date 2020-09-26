package ca.ulaval.glo4003.domain.user.exception;

public class InvalidUserException extends RuntimeException {
  public String error;
  public String description;

  public InvalidUserException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
