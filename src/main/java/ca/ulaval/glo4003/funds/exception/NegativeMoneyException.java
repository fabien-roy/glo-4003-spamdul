package ca.ulaval.glo4003.funds.exception;

public class NegativeMoneyException extends FundException {
  private static final String ERROR = "Negative amount of money";
  private static final String DESCRIPTION = "Amount of cannot be negative";

  public NegativeMoneyException() {
    super(ERROR, DESCRIPTION);
  }
}
