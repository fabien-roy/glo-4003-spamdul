package ca.ulaval.glo4003.communications.domain.exceptions;

public abstract class CommunicationException extends RuntimeException {
  public String error;
  public String description;

  public CommunicationException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
