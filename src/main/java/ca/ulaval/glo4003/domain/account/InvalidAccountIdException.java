package ca.ulaval.glo4003.domain.account;

public class InvalidAccountIdException extends Exception {
  public InvalidAccountIdException() {
    super("Invalid account id");
  }
}
