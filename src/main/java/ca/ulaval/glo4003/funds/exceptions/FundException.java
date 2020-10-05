package ca.ulaval.glo4003.funds.exceptions;

public class FundException extends RuntimeException {
  public final String error;
  public final String description;

  FundException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
