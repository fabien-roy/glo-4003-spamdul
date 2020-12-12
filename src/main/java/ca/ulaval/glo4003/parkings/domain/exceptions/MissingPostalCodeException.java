package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class MissingPostalCodeException extends ApplicationException {
  private static final String ERROR = "Missing property : postalCode";
  private static final String DESCRIPTION = "Property postalCode is missing";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public MissingPostalCodeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
