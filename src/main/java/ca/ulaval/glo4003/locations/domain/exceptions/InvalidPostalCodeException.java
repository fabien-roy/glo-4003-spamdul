package ca.ulaval.glo4003.locations.domain.exceptions;

public class InvalidPostalCodeException extends LocationException {
  public static final String ERROR = "Invalid postal code";
  public static final String DESCRIPTION = "Postal code is invalid";

  public InvalidPostalCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
