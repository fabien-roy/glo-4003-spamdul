package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidConsumptionTypeException extends ApplicationException {
  private static final String ERROR = "Invalid car consumption type";
  private static final String DESCRIPTION = "Car consumption should be one of %s";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidConsumptionTypeException() {
    super(ERROR, DESCRIPTION, CODE);
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, enumerateValues(ConsumptionType.class));
  }
}
