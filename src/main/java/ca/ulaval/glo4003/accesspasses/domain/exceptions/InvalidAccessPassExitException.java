package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidAccessPassExitException extends ApplicationException {
  private static final String ERROR = "Invalid access pass exit";
  private static final String DESCRIPTION = "This access pass has never been admitted on campus";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidAccessPassExitException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
