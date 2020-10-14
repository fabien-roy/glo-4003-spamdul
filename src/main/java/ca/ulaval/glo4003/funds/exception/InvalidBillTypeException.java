package ca.ulaval.glo4003.funds.exception;

public class InvalidBillTypeException extends FundsException {
  private static final String ERROR = "Invalid bill type";
  private static final String DESCRIPTION = "Bill was not found";

  public InvalidBillTypeException() {
    super(ERROR, DESCRIPTION);
  }
}
