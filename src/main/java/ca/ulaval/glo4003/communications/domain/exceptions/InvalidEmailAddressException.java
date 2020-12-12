package ca.ulaval.glo4003.communications.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidEmailAddressException extends ApplicationException {
  private static final String ERROR = "Invalid email address";
  private static final String DESCRIPTION = "Email address must be a valid email format";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidEmailAddressException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
