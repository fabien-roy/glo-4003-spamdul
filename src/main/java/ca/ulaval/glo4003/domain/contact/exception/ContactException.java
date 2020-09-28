package ca.ulaval.glo4003.domain.contact.exception;

public abstract class ContactException extends RuntimeException {
  public final String error;
  public final String description;

  public ContactException(String error, String description) {
    super(error);
    this.error = error;
    this.description = description;
  }
}
