package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Write valid enums
public class InvalidConsumptionTypeException extends ApplicationException {
  private static final String ERROR = "Invalid car consumption type";
  private static final String DESCRIPTION =
      "Car consumption type must be greedy, economic, economical hybrid, super economical or 0 pollution";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidConsumptionTypeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
