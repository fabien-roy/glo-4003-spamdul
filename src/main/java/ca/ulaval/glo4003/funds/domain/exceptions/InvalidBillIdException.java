package ca.ulaval.glo4003.funds.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidBillIdException extends ApplicationException {
  private static final String ERROR = "Invalid bill id";
  private static final String DESCRIPTION = "Bill id should be a GUID";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidBillIdException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
