package ca.ulaval.glo4003.domain.account;

public class NotFoundAccountException extends RuntimeException {
  public NotFoundAccountException() {
    super("Account not found");
  }
}
