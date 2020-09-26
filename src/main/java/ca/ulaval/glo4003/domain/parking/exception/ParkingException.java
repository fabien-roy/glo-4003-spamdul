package ca.ulaval.glo4003.domain.parking.exception;

public class ParkingException extends RuntimeException {
  public String error;
  public String description;

  ParkingException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
