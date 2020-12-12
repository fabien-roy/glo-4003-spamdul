package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Write valid format
public class InvalidLicensePlateException extends ApplicationException {
  private static final String ERROR = "Invalid license plate";
  private static final String DESCRIPTION = "License plate must be of valid format";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidLicensePlateException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
