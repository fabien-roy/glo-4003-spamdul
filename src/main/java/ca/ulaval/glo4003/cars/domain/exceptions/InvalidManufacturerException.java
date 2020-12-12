package ca.ulaval.glo4003.cars.domain.exceptions;

public class InvalidManufacturerException extends CarException {
  private static final String ERROR = "Invalid manufacturer";
  private static final String DESCRIPTION = "Manufacturer is invalid";

  public InvalidManufacturerException() {
    super(ERROR, DESCRIPTION);
  }
}
