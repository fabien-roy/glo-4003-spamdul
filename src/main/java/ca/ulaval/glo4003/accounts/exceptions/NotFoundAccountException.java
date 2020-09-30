package ca.ulaval.glo4003.accounts.exceptions;

public class NotFoundAccountException extends AccountException {
  private static final String ERROR = "Account not found";
  private static final String DESCRIPTION = "Account was not found in repository";

  public NotFoundAccountException() {
    super(ERROR, DESCRIPTION);
  }
}
