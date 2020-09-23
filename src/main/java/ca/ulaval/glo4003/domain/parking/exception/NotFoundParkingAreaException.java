package ca.ulaval.glo4003.domain.parking.exception;

public class NotFoundParkingAreaException extends RuntimeException {
  public NotFoundParkingAreaException() {
    super("Parking area not found");
  }
}
