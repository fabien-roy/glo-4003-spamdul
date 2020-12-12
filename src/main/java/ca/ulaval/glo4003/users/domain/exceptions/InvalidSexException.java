package ca.ulaval.glo4003.users.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Get enum values
public class InvalidSexException extends ApplicationException {
  private static final String ERROR = "Invalid sex";
  private static final String DESCRIPTION = "Sex should be m, f or x";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidSexException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
