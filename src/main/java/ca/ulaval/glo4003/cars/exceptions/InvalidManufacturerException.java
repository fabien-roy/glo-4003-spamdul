package ca.ulaval.glo4003.cars.exceptions;

public class InvalidManufacturerException extends CarException {
  private static final String ERROR = "Invalid manufacturer";
  private static final String DESCRIPTION = "Must not be null";

  public InvalidManufacturerException() {
    super(ERROR, DESCRIPTION);
  }
}
