package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #306 : Write valid format
public class InvalidTimeOfDayException extends ApplicationException {
  private static final String ERROR = "Invalid time of day";
  private static final String DESCRIPTION = "Invalid time of day";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidTimeOfDayException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
