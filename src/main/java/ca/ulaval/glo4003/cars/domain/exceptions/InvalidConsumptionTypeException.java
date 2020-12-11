package ca.ulaval.glo4003.cars.domain.exceptions;

public class InvalidConsumptionTypeException extends CarException {
  private static final String ERROR = "Invalid car consumption type";
  private static final String DESCRIPTION =
      "Car consumption type must be greedy, economic, economical hybrid, super economical or 0 pollution";

  public InvalidConsumptionTypeException() {
    super(ERROR, DESCRIPTION);
  }
}
