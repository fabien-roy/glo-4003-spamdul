package ca.ulaval.glo4003.domain.parking.exception;

public class NotFoundParkingAreaException extends ParkingException {
  private static final String ERROR = "Parking area not found";
  private static final String DESCRIPTION = "Parking area was not found";

  public NotFoundParkingAreaException() {
    super(ERROR, DESCRIPTION);
  }
}
