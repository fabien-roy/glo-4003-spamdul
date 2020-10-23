package ca.ulaval.glo4003.funds.exception;

public class SustainableMobilityProgramBankInsufficientAmountException extends FundsException {
  private static final String ERROR = "Insufficient amount";
  private static final String DESCRIPTION =
      "Sustainable mobility program bank has an insufficient amount";

  public SustainableMobilityProgramBankInsufficientAmountException() {
    super(ERROR, DESCRIPTION);
  }
}
