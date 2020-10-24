package ca.ulaval.glo4003.funds.exception;

public class BillNotFoundException extends FundException {
  private static final String ERROR = "Bill not found";
  private static final String DESCRIPTION = "Bill was not found";

  public BillNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
