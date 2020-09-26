package ca.ulaval.glo4003.domain.account.exception;

public class AccountException extends RuntimeException {
  public String error;
  public String description;

  AccountException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
