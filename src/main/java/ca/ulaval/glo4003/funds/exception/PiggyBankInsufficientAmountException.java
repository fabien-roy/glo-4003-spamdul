package ca.ulaval.glo4003.funds.exception;

public class PiggyBankInsufficientAmountException extends FundsException {
  private static final String ERROR = "Insufficient amount ";
  private static final String DESCRIPTION = "Piggy bank has an insufficient amount";

  public PiggyBankInsufficientAmountException() {
    super(ERROR, DESCRIPTION);
  }
}
