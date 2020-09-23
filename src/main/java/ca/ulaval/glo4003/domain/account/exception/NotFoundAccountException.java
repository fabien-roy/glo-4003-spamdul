package ca.ulaval.glo4003.domain.account.exception;

public class NotFoundAccountException extends RuntimeException {
  public NotFoundAccountException() {
    super("Account not found");
  }
}
