package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class WrongReceptionMethodForBicycleAccessPassException extends ApplicationException {
  private static final String ERROR = "Wrong reception method for bicycle access pass";
  private static final String DESCRIPTION =
      "The reception method provided doesn't match any reception method";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public WrongReceptionMethodForBicycleAccessPassException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
