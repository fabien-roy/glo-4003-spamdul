package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidManufacturerException extends ApplicationException {
  private static final String ERROR = "Invalid manufacturer";
  private static final String DESCRIPTION = "Manufacturer is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidManufacturerException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
