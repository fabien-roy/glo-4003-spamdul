package ca.ulaval.glo4003.initiative.exception;

public class InitiativeException extends RuntimeException {
  public String error;
  public String description;

  public InitiativeException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
