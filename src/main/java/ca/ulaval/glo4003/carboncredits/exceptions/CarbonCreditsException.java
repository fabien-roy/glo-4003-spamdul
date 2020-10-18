package ca.ulaval.glo4003.carboncredits.exceptions;

public abstract class CarbonCreditsException extends RuntimeException {
  public String error;
  public String description;

  public CarbonCreditsException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
