package ca.ulaval.glo4003.initiatives.domain.exceptions;

public class InitiativeException extends RuntimeException {
  public String error;
  public String description;

  public InitiativeException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
