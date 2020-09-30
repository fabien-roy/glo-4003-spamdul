package ca.ulaval.glo4003.domain.offense.exceptions;

public abstract class OffenseException extends RuntimeException {
  public final String error;
  public final String description;

  public OffenseException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
