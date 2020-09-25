package ca.ulaval.glo4003.domain.account.exception;

public class InvalidAccountIdException extends RuntimeException {
  public InvalidAccountIdException() {
    super("Invalid account id");
  }
}
