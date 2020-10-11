package ca.ulaval.glo4003.funds.exception;

public class BillNotFound extends FundsException {
  private static final String ERROR = "Bill not found";
  private static final String DESCRIPTION = "Bill was not found";

  public BillNotFound() {
    super(ERROR, DESCRIPTION);
  }
}
