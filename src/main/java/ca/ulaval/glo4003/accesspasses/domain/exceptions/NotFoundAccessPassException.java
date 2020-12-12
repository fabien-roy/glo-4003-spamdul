package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundAccessPassException extends ApplicationException {
  private static final String ERROR = "Access pass not found";
  private static final String DESCRIPTION = "Access pass was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public NotFoundAccessPassException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
