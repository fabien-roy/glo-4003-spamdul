package ca.ulaval.glo4003.cars.domain.exceptions;

public class AlreadyExistingCarException extends CarException {
  private static final String ERROR = "Already existing car";
  private static final String DESCRIPTION = "There already exists a car with this license plate";

  public AlreadyExistingCarException() {
    super(ERROR, DESCRIPTION);
  }
}
