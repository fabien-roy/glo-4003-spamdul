package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundCarException extends ApplicationException {
  private static final String ERROR = "Car not found";
  private static final String DESCRIPTION = "Car with license plate %s was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private final LicensePlate licensePlate;

  public NotFoundCarException(LicensePlate licensePlate) {
    super(ERROR, DESCRIPTION, CODE);
    this.licensePlate = licensePlate;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, licensePlate.toString());
  }
}
