package ca.ulaval.glo4003.cars.exceptions;

public class InvalidConsumptionTypeException extends CarException {
  private static final String ERROR = "Invalid car consumption";
  private static final String DESCRIPTION =
      "Car consumption muste be gourmande, économique, hybride économique, super économique or 0 pollution";

  public InvalidConsumptionTypeException() {
    super(ERROR, DESCRIPTION);
  }
}
