package ca.ulaval.glo4003.funds.exception;

public class InvalidBillQueryParamException extends FundException {
  public static final String ERROR = "Invalid bill query param";
  public static final String DESCRIPTION = "Bill query param is not supported";

  public InvalidBillQueryParamException() {
    super(ERROR, DESCRIPTION);
  }
}
