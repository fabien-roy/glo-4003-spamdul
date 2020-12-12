package ca.ulaval.glo4003.users.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class FutureBirthDateException extends ApplicationException {
  private static final String ERROR = "Future birth date";
  private static final String DESCRIPTION = "Birth date cannot be set in the future";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public FutureBirthDateException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
