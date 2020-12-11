package ca.ulaval.glo4003.parkings.domain.exceptions;

public class InvalidParkingStickerCodeException extends ParkingException {
  private static final String ERROR = "Invalid parking sticker code";
  private static final String DESCRIPTION = "Parking sticker code is invalid";

  public InvalidParkingStickerCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
