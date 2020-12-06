package ca.ulaval.glo4003.cars.exceptions;

public class NotFoundCarException extends CarException {
  private static final String ERROR = "Car not found";
  private static final String DESCRIPTION = "There is no car with this license plate";

  public NotFoundCarException() {
    super(ERROR, DESCRIPTION);
  }
}
