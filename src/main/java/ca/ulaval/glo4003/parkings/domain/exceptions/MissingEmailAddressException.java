package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class MissingEmailAddressException extends ApplicationException {
  private static final String ERROR = "Missing property : emailAddress";
  private static final String DESCRIPTION = "Property emailAddress is missing";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public MissingEmailAddressException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
