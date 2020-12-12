package ca.ulaval.glo4003.funds.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class AmountDueExceededException extends ApplicationException {
  private static final String ERROR = "Amount due exceeded";
  private static final String DESCRIPTION = "The amount paid exceeds the amount requested";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public AmountDueExceededException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
