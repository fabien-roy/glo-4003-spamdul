package ca.ulaval.glo4003.domain.location.exception;

public class InvalidPostalCodeException extends RuntimeException {
  public InvalidPostalCodeException() {
    super("Invalid postal code");
  }
}
