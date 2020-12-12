package ca.ulaval.glo4003.funds.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InsufficientAvailableMoneyException extends ApplicationException {
  private static final String ERROR = "Insufficient available money";
  private static final String DESCRIPTION =
      "Not enough available money to remove the requested amount";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InsufficientAvailableMoneyException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
