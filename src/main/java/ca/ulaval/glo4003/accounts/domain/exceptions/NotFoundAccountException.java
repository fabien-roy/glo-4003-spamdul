package ca.ulaval.glo4003.accounts.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundAccountException extends ApplicationException {
  private static final String ERROR = "Account not found";
  private static final String DESCRIPTION = "Account was not found in repository";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public NotFoundAccountException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
