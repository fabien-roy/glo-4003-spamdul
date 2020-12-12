package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidModelException extends ApplicationException {
  private static final String ERROR = "Invalid model";
  private static final String DESCRIPTION = "Model is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidModelException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
