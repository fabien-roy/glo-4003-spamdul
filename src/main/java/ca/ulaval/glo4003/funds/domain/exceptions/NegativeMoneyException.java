package ca.ulaval.glo4003.funds.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NegativeMoneyException extends ApplicationException {
  private static final String ERROR = "Negative amount of money";
  private static final String DESCRIPTION = "Amount of money cannot be negative";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public NegativeMoneyException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
