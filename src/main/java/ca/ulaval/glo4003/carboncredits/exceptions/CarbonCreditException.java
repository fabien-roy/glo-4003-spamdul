package ca.ulaval.glo4003.carboncredits.exceptions;

public abstract class CarbonCreditException extends RuntimeException {
  public String error;
  public String description;

  public CarbonCreditException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
