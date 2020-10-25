package ca.ulaval.glo4003.funds.exception;

public class InvalidYearException extends FundException {
  private static final String ERROR = "Invalid year";
  private static final String DESCRIPTION = "Year must be a positive integer";

  public InvalidYearException() {
    super(ERROR, DESCRIPTION);
  }
}
