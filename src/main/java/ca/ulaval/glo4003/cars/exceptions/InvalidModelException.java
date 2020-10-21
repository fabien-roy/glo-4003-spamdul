package ca.ulaval.glo4003.cars.exceptions;

public class InvalidModelException extends CarException {
  private static final String ERROR = "Invalid model";
  private static final String DESCRIPTION = "Model is invalid";

  public InvalidModelException() {
    super(ERROR, DESCRIPTION);
  }
}
