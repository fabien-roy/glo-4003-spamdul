package ca.ulaval.glo4003.domain.offense.exceptions;

public abstract class InvalidOffenseException extends RuntimeException {
  private final String error;
  private final String description;

  public InvalidOffenseException(String error, String description) {
    super(error);
    this.error = error;
    this.description = description;
  }
}
