package ca.ulaval.glo4003.parkings.exceptions;

public class NotFoundParkingStickerException extends ParkingException {
  private static final String ERROR = "Parking sticker not found";
  private static final String DESCRIPTION = "Parking sticker was not found";

  public NotFoundParkingStickerException() {
    super(ERROR, DESCRIPTION);
  }
}
