package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundCarException extends ApplicationException {
  private static final String ERROR = "Car not found";
  private static final String DESCRIPTION = "There is no car with this license plate";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public NotFoundCarException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
