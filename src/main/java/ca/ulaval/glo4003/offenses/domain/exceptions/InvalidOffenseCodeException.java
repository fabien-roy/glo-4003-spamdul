package ca.ulaval.glo4003.offenses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidOffenseCodeException extends ApplicationException {
  private static final String ERROR = "Invalid offense code";
  private static final String DESCRIPTION = "Offense code is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidOffenseCodeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
