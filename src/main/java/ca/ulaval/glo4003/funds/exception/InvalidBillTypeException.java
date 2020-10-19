package ca.ulaval.glo4003.funds.exception;

public class InvalidBillTypeException extends FundsException {
  private static final String ERROR = "Invalid bill type";
  private static final String DESCRIPTION = "Bill type is not valid";

  public InvalidBillTypeException() {
    super(ERROR, DESCRIPTION);
  }
}
