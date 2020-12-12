package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Write valid enums
public class InvalidReceptionMethodException extends ApplicationException {
  private static final String ERROR = "Invalid reception method";
  private static final String DESCRIPTION = "Reception method should be postal or email";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidReceptionMethodException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
