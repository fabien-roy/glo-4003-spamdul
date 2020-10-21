package ca.ulaval.glo4003.cars.exceptions;

public class InvalidModelException extends CarException {
  private static final String ERROR = "Invalid model";
  private static final String DESCRIPTION = "Must not be null";

  public InvalidModelException() {
    super(ERROR, DESCRIPTION);
  }
}
