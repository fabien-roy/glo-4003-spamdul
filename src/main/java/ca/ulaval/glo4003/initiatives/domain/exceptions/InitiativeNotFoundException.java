package ca.ulaval.glo4003.initiatives.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InitiativeNotFoundException extends ApplicationException {
  private static final String ERROR = "Initiative not found";
  private static final String DESCRIPTION = "Initiative was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public InitiativeNotFoundException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
