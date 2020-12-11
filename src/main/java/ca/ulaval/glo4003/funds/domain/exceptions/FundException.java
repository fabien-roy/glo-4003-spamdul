package ca.ulaval.glo4003.funds.domain.exceptions;

public abstract class FundException extends RuntimeException {
  public String error;
  public String description;

  public FundException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
