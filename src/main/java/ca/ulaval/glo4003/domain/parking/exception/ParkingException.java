package ca.ulaval.glo4003.domain.parking.exception;

public abstract class ParkingException extends RuntimeException {
  public final String error;
  public final String description;

  ParkingException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
