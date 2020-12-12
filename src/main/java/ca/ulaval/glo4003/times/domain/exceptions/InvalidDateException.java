package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidDateException extends ApplicationException {
  private static final String ERROR = "Invalid date";
  private static final String DESCRIPTION = "Date must be of format : %s";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;
  private final String format;

  public InvalidDateException(String format) {
    super(ERROR, DESCRIPTION, CODE);
    this.format = format;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, format);
  }
}
