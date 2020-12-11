package ca.ulaval.glo4003.accounts.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidAccountIdException extends ApplicationException {
  private static final String ERROR = "Invalid account id";
  private static final String DESCRIPTION = "Account Id is invalid";

  public InvalidAccountIdException() {
    super(ERROR, DESCRIPTION);
  }

  @Override
  public ErrorCode getErrorCode() {
    return ErrorCode.INVALID_REQUEST;
  }
}
