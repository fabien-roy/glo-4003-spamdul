package ca.ulaval.glo4003.cars.exceptions;

public class AlreadyExistingLicensePlateException extends CarException {
  private static final String ERROR = "Already existing license plate";
  private static final String DESCRIPTION = "License plate must not already exist";

  public AlreadyExistingLicensePlateException() {
    super(ERROR, DESCRIPTION);
  }
}
