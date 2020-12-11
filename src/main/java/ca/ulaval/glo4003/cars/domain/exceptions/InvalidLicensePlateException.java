package ca.ulaval.glo4003.cars.domain.exceptions;

public class InvalidLicensePlateException extends CarException {
  private static final String ERROR = "Invalid license plate";
  private static final String DESCRIPTION = "License plate must be of valid format";

  public InvalidLicensePlateException() {
    super(ERROR, DESCRIPTION);
  }
}
