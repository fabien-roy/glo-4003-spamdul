package ca.ulaval.glo4003.users.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Send format
public class InvalidBirthDateException extends ApplicationException {
  private static final String ERROR = "Invalid birth date";
  private static final String DESCRIPTION = "Birth date should be in this format : dd-mm-yyyy";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidBirthDateException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
