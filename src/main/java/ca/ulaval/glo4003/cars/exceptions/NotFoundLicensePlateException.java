package ca.ulaval.glo4003.cars.exceptions;

public class NotFoundLicensePlateException extends CarException {
  private static final String ERROR = "License plate not found";
  private static final String DESCRIPTION = "There is no car with this license plate";

  public NotFoundLicensePlateException() {
    super(ERROR, DESCRIPTION);
  }
}
