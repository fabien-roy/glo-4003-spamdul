package ca.ulaval.glo4003.accounts.domain.exceptions;

public abstract class AccountException extends RuntimeException {
  public final String error;
  public final String description;

  AccountException(String error, String description) {
    super(error);
    this.error = error;
    this.description = description;
  }
}
