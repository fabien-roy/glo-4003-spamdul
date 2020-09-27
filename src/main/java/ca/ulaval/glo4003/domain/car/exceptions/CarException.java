package ca.ulaval.glo4003.domain.car.exceptions;

public abstract class CarException extends RuntimeException {
  public final String error;
  public final String description;

  public CarException(String error, String description) {
    super(error);
    this.error = error;
    this.description = description;
  }
}
