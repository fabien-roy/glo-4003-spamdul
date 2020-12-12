package ca.ulaval.glo4003.carboncredits.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Write valid enums
public class InvalidMonthlyPaymentStatusException extends ApplicationException {
  private static final String ERROR = "Invalid monthly payment status";
  private static final String DESCRIPTION = "Monthly payment status is not valid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidMonthlyPaymentStatusException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
