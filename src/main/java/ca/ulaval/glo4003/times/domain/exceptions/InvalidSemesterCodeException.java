package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #306 : Write valid format
public class InvalidSemesterCodeException extends ApplicationException {
  private static final String ERROR = "Invalid semester code";
  private static final String DESCRIPTION = "Semester code is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidSemesterCodeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
