package ca.ulaval.glo4003.domain.parking.exception;

public class MissingPostalCodeException extends RuntimeException {
  public MissingPostalCodeException() {
    super("Missing property : postalCode");
  }
}
