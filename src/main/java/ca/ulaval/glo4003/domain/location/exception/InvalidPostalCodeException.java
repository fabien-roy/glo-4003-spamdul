package ca.ulaval.glo4003.domain.location.exception;

public class InvalidPostalCodeException extends RuntimeException {
  public String error = "Invalid postal code";
  public String description = "Postal code is invalid";

  public InvalidPostalCodeException() {}
}
