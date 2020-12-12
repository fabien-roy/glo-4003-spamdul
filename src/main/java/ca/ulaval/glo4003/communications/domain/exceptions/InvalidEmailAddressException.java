package ca.ulaval.glo4003.communications.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidEmailAddressException extends ApplicationException {
  public static final String ERROR = "Invalid email address";
  public static final String DESCRIPTION = "Email address is invalid";
  public static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidEmailAddressException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
