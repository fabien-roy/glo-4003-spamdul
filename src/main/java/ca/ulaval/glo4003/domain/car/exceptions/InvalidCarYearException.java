package ca.ulaval.glo4003.domain.car.exceptions;

public class InvalidCarYearException extends CarException {
  private static final String ERROR = "Invalid year";
  private static final String DESCRIPTION = "Must be before %d";

  public InvalidCarYearException(int year) {
    super(ERROR, String.format(DESCRIPTION, year));
  }
}
