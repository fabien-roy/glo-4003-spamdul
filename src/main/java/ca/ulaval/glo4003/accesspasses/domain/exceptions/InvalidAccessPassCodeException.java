package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidAccessPassCodeException extends ApplicationException {
  private static final String ERROR = "Invalid access pass code";
  private static final String DESCRIPTION = "Access pass code is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidAccessPassCodeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
