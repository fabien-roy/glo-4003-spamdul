package ca.ulaval.glo4003.parkings.domain.exceptions;

public class InvalidParkingAreaCodeException extends ParkingException {
  private static final String ERROR = "Invalid parking area code";
  private static final String DESCRIPTION = "Parking area code is invalid";

  public InvalidParkingAreaCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
