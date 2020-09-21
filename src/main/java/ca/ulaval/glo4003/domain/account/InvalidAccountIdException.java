package ca.ulaval.glo4003.domain.account;

public class InvalidAccountIdException extends RuntimeException {
  public InvalidAccountIdException() {
    super("Invalid account id");
  }
}
