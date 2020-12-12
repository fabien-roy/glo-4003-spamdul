package ca.ulaval.glo4003.offenses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class OffenseTypeNotFoundException extends ApplicationException {
  private static final String ERROR = "Offense type not found";
  private static final String DESCRIPTION =
      "The offense code used does not belong to an existing offense type";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public OffenseTypeNotFoundException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
