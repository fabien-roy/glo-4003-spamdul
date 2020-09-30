package ca.ulaval.glo4003.users.exceptions;

public abstract class UserException extends RuntimeException {
  public final String error;
  public final String description;

  public UserException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
