package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidAccessPassCodeException extends ApplicationException {
  private static final String ERROR = "Invalid access pass code";
  private static final String DESCRIPTION = "Access pass code is invalid";

  public InvalidAccessPassCodeException() {
    super(ERROR, DESCRIPTION);
  }

  @Override
  public ErrorCode getErrorCode() {
    return ErrorCode.INVALID_REQUEST;
  }
}
