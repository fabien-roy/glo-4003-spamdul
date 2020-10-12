package ca.ulaval.glo4003.cars.exceptions;

public class LicensePlateNotFoundException extends CarException {
  private static final String ERROR = "License Plate not found";
  private static final String DESCRIPTION = "There is no car with this license plate";

  public LicensePlateNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
