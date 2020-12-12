package ca.ulaval.glo4003.funds.domain.exceptions;

public class AmountDueExceededException extends FundException {
  private static final String ERROR = "Amount due exceeded";
  private static final String DESCRIPTION = "The amount paid exceeds the amount requested";

  public AmountDueExceededException() {
    super(ERROR, DESCRIPTION);
  }
}
