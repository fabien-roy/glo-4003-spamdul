package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidCarYearException extends ApplicationException {
  private static final String ERROR = "Invalid year";
  private static final String DESCRIPTION = "Year must be before %d";
  private static final String DESCRIPTION_NULL = "Year must not be null";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidCarYearException(int year) {
    super(ERROR, String.format(DESCRIPTION, year), CODE);
  }

  public InvalidCarYearException() {
    super(ERROR, DESCRIPTION_NULL, CODE);
  }
}
