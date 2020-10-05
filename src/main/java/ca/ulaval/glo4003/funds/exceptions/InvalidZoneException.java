package ca.ulaval.glo4003.funds.exceptions;

public class InvalidZoneException extends FundException {
  private static final String ERROR = "Invalid Zone";
  private static final String DESCRIPTION = "Specified zone is invalid";

  public InvalidZoneException() {
    super(ERROR, DESCRIPTION);
  }
}
