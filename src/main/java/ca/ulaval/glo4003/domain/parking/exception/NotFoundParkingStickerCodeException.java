package ca.ulaval.glo4003.domain.parking.exception;

public class NotFoundParkingStickerCodeException extends ParkingException {
  private static final String ERROR = "Parking sticker not found";
  private static final String DESCRIPTION = "Parking sticker was not found";

  public NotFoundParkingStickerCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
