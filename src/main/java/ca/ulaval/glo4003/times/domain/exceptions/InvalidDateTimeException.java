package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Send format
public class InvalidDateTimeException extends ApplicationException {
  private static final String ERROR = "Invalid date time";
  private static final String DESCRIPTION = "Date time must be of format 'dd-MM-yyyy HH:mm:ss'";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidDateTimeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
