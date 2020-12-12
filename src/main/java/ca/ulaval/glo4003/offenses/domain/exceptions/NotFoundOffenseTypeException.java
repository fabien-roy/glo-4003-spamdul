package ca.ulaval.glo4003.offenses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;

public class NotFoundOffenseTypeException extends ApplicationException {
  private static final String ERROR = "Offense type not found";
  private static final String DESCRIPTION = "Offense type with code %s was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private final OffenseCode offenseCode;

  public NotFoundOffenseTypeException(OffenseCode offenseCode) {
    super(ERROR, DESCRIPTION, CODE);
    this.offenseCode = offenseCode;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, offenseCode.toString());
  }
}
