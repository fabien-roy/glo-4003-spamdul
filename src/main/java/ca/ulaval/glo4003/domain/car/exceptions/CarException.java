package ca.ulaval.glo4003.domain.car.exceptions;

public class CarException extends RuntimeException {

  public String error;
  public String description;

  public CarException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
