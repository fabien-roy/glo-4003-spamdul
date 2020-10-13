package ca.ulaval.glo4003.gateentries.exceptions;

public class InvalidDayOfWeekException extends GateEntryException {
  private static final String ERROR = "Invalid day of week";
  private static final String DESCRIPTION = "Day of week is invalid";

  public InvalidDayOfWeekException() {
    super(ERROR, DESCRIPTION);
  }
}
