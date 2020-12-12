package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidSemesterCodeException extends ApplicationException {
  private static final String ERROR = "Invalid semester code";
  private static final String DESCRIPTION = "Semester code must be of format : %s";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;
  private final String format;

  public InvalidSemesterCodeException(String format) {
    super(ERROR, DESCRIPTION, CODE);
    this.format = format;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, format);
  }
}
