package ca.ulaval.glo4003.piggybank.exceptions;

public class PiggyBankException extends RuntimeException {
  public final String error;
  public final String description;

  public PiggyBankException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
