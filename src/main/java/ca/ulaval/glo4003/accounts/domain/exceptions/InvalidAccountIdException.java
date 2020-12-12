package ca.ulaval.glo4003.accounts.domain.exceptions;

public class InvalidAccountIdException extends AccountException {
  private static final String ERROR = "Invalid account id";
  private static final String DESCRIPTION = "Account Id is invalid";

  public InvalidAccountIdException() {
    super(ERROR, DESCRIPTION);
  }
}
