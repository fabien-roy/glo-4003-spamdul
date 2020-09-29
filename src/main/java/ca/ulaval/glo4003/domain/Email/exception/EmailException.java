package ca.ulaval.glo4003.domain.Email.exception;

public class EmailException extends RuntimeException {
  public final String error;
  public final String description;

  public EmailException(String error, String description) {
    super(error);
    this.error = error;
    this.description = description;
  }
}
