package ca.ulaval.glo4003.cars.exceptions;

public class NotFoundLicensePlate extends CarException {
  private static final String ERROR = "License Plate not found";
  private static final String DESCRIPTION = "There is no car with this license plate";

  public NotFoundLicensePlate() {
    super(ERROR, DESCRIPTION);
  }
}
