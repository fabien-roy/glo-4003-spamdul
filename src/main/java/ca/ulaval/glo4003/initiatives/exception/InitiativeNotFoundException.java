package ca.ulaval.glo4003.initiatives.exception;

public class InitiativeNotFoundException extends InitiativeException {
  private static final String ERROR = "Initiative not found";
  private static final String DESCRIPTION = "Initiative was not found";

  public InitiativeNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
