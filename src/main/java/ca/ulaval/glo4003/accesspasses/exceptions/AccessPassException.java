package ca.ulaval.glo4003.accesspasses.exceptions;

public abstract class AccessPassException extends RuntimeException {
  public final String error;
  public final String description;

  AccessPassException(String error, String description) {
    super(error);
    this.error = error;
    this.description = description;
  }
}
