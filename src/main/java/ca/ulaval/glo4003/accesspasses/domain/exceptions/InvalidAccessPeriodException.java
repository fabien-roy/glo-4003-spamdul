package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidAccessPeriodException extends ApplicationException {
  private static final String ERROR = "Invalid access period";
  private static final String DESCRIPTION = "This period is not valid for an access pass";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidAccessPeriodException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
