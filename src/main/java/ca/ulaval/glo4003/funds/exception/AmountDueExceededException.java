package ca.ulaval.glo4003.funds.exception;

public class AmountDueExceededException extends FundsException {
  private static final String ERROR = "Amount due exceeded";
  private static final String DESCRIPTION = "The amount paid exceeds the amount requested";

  public AmountDueExceededException() {
    super(ERROR, DESCRIPTION);
  }
}
