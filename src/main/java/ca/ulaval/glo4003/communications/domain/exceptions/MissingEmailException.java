package ca.ulaval.glo4003.communications.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class MissingEmailException extends ApplicationException {
  private static final String ERROR = "Missing property : email";
  private static final String DESCRIPTION = "Property email is missing";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public MissingEmailException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
