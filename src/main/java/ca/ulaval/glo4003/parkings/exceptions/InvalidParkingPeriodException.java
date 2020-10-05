package ca.ulaval.glo4003.parkings.exceptions;

public class InvalidParkingPeriodException extends ParkingException {
  private static final String ERROR = "Invalid parking period";
  private static final String DESCRIPTION = "Parking period is invalid";

  public InvalidParkingPeriodException() {
    super(ERROR, DESCRIPTION);
  }
}
