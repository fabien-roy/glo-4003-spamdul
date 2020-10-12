package ca.ulaval.glo4003.funds.exception;

public class AmountDueExceededException extends FundsException {
  private static final String ERROR = "Too much money";
  private static final String DESCRIPTION = "There is too much money to paid this bill";

  public AmountDueExceededException() {
    super(ERROR, DESCRIPTION);
  }
}
