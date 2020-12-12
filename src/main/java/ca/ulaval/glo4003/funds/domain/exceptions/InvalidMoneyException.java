package ca.ulaval.glo4003.funds.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidMoneyException extends ApplicationException {
  private static final String ERROR = "Invalid amount of money";
  private static final String DESCRIPTION = "Amount of money is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidMoneyException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
