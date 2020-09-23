package ca.ulaval.glo4003.domain.car.exceptions;

public class InvalidCarException extends RuntimeException {

  private String error;
  private String description;

  public InvalidCarException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
