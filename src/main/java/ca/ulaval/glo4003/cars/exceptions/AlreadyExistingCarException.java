package ca.ulaval.glo4003.cars.exceptions;

public class AlreadyExistingCarException extends CarException {
  private static final String ERROR = "Already existing car";
  private static final String DESCRIPTION = "Car must not already exist";

  public AlreadyExistingCarException() {
    super(ERROR, DESCRIPTION);
  }
}
