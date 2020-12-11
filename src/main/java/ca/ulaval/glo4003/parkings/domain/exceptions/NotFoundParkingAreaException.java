package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundParkingAreaException extends ApplicationException {
  private static final String ERROR = "Parking area not found";
  private static final String DESCRIPTION = "Parking area was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public NotFoundParkingAreaException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
