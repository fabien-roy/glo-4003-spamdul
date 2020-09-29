package ca.ulaval.glo4003.domain.parking.exception;

public class InvalidParkingStickerCodeException extends ParkingException {
  private static final String ERROR = "Invalid parking sticker parkingStickerCode";
  private static final String DESCRIPTION = "Parking sticker parkingStickerCode is invalid";

  public InvalidParkingStickerCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
