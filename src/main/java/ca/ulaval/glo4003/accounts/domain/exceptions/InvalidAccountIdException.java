package ca.ulaval.glo4003.accounts.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidAccountIdException extends ApplicationException {
  private static final String ERROR = "Invalid account id";
  private static final String DESCRIPTION = "Account Id is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidAccountIdException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
