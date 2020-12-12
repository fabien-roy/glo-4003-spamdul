package ca.ulaval.glo4003.funds.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundBillException extends ApplicationException {
  private static final String ERROR = "Bill not found";
  private static final String DESCRIPTION = "Bill was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public NotFoundBillException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
