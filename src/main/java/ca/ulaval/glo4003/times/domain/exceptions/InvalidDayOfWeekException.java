package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidDayOfWeekException extends ApplicationException {
  private static final String ERROR = "Invalid day of week";
  private static final String DESCRIPTION = "Day of week is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidDayOfWeekException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
