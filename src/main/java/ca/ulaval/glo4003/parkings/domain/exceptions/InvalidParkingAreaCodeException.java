package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidParkingAreaCodeException extends ApplicationException {
  private static final String ERROR = "Invalid parking area code";
  private static final String DESCRIPTION = "Parking area code cannot be empty";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidParkingAreaCodeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
