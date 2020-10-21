package ca.ulaval.glo4003.cars.exceptions;

public class InvalidCarYearException extends CarException {
  private static final String ERROR = "Invalid year";
  private static final String DESCRIPTION = "Year must be before %d";
  private static final String DESCRIPTION_NULL = "Year must not be null";

  public InvalidCarYearException(int year) {
    super(ERROR, String.format(DESCRIPTION, year));
  }

  public InvalidCarYearException() {
    super(ERROR, DESCRIPTION_NULL);
  }
}
