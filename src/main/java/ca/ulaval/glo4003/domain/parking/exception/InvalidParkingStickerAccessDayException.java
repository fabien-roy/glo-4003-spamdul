package ca.ulaval.glo4003.domain.parking.exception;

public class InvalidParkingStickerAccessDayException extends ParkingException {
  private static final String ERROR = "Invalid parking sticker acess day";
  private static final String DESCRIPTION = "Parking sticker access day is invalid";

  public InvalidParkingStickerAccessDayException() {
    super(ERROR, DESCRIPTION);
  }
}
