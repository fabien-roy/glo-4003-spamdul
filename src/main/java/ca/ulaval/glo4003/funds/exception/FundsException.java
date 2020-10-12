package ca.ulaval.glo4003.funds.exception;

public abstract class FundsException extends RuntimeException {
  public String error;
  public String description;

  public FundsException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
