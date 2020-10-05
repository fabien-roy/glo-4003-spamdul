package ca.ulaval.glo4003.funds.exceptions;

public class InvalidTimeException extends FundException {
  private static final String ERROR = "Invalid time";
  private static final String DESCRIPTION = "Specified time is invalid";

  public InvalidTimeException() {
    super(ERROR, DESCRIPTION);
  }
}
