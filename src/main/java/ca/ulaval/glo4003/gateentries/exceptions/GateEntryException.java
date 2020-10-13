package ca.ulaval.glo4003.gateentries.exceptions;

// TODO : Add GateEntryExceptionMapper
public abstract class GateEntryException extends RuntimeException {
  public final String error;
  public final String description;

  GateEntryException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
