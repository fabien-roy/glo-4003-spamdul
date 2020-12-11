package ca.ulaval.glo4003.initiatives.domain.exceptions;

public class InvalidInitiativeNameException extends InitiativeException {
  private static final String ERROR = "Invalid initiative name";
  private static final String DESCRIPTION = "Initiative name cannot be null";

  public InvalidInitiativeNameException() {
    super(ERROR, DESCRIPTION);
  }
}
