package ca.ulaval.glo4003.domain.bill.exceptions;

public class InvalidZoneException extends BillException {
  private static final String ERROR = "Invalid Zone";
  private static final String DESCRIPTION = "Specified zone is invalid";

  public InvalidZoneException() {
    super(ERROR, DESCRIPTION);
  }
}
