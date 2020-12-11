package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidParkingPeriodException extends ApplicationException {
  private static final String ERROR = "Invalid parking period";
  private static final String DESCRIPTION = "Parking period is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidParkingPeriodException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
