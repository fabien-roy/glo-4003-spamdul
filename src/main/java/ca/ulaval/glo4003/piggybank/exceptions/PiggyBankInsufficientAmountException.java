package ca.ulaval.glo4003.piggybank.exceptions;

public class PiggyBankInsufficientAmountException extends PiggyBankException {
  private static final String ERROR = "Insufficient amount ";
  private static final String DESCRIPTION = "Piggy bank has an insufficient amount";

  public PiggyBankInsufficientAmountException() {
    super(ERROR, DESCRIPTION);
  }
}
