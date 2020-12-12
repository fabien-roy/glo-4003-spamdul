package ca.ulaval.glo4003.users.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidNameException extends ApplicationException {
  private static final String NAME = "Invalid name";
  private static final String DESCRIPTION = "Name cannot be empty";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidNameException() {
    super(NAME, DESCRIPTION, CODE);
  }
}
