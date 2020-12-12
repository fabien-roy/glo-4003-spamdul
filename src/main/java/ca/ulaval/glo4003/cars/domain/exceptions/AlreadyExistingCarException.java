package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class AlreadyExistingCarException extends ApplicationException {
  private static final String ERROR = "Already existing car";
  private static final String DESCRIPTION = "There already exists a car with this license plate";
  private static final ErrorCode CODE = ErrorCode.ALREADY_EXISTING;

  public AlreadyExistingCarException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
