package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidDateException extends ApplicationException {
  private static final String ERROR = "Invalid date";
  private static final String DESCRIPTION = "Date must be of format 'dd-MM-yyyy'";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidDateException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
