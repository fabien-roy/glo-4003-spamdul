package ca.ulaval.glo4003.access.exceptions;

public abstract class AccessException extends RuntimeException {
  public final String error;
  public final String description;

  AccessException(String error, String description) {
    super(error);
    this.error = error;
    this.description = description;
  }
}
