package ca.ulaval.glo4003.initiatives.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;

public class NotFoundInitiativeException extends ApplicationException {
  private static final String ERROR = "Initiative not found";
  private static final String DESCRIPTION = "Initiative with code %s was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private final InitiativeCode initiativeCode;

  public NotFoundInitiativeException(InitiativeCode initiativeCode) {
    super(ERROR, DESCRIPTION, CODE);
    this.initiativeCode = initiativeCode;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, initiativeCode.toString());
  }
}
