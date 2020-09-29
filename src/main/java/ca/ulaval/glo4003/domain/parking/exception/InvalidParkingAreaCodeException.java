package ca.ulaval.glo4003.domain.parking.exception;

public class InvalidParkingAreaCodeException extends ParkingException {
  private static final String ERROR = "Invalid parking area parkingStickerCode";
  private static final String DESCRIPTION = "Parking area parkingStickerCode is invalid";

  public InvalidParkingAreaCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
