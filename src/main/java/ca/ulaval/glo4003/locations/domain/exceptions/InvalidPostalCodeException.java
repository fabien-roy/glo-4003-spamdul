package ca.ulaval.glo4003.locations.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Write valid format
public class InvalidPostalCodeException extends ApplicationException {
  private static final String ERROR = "Invalid postal code";
  private static final String DESCRIPTION = "Postal code is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidPostalCodeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
