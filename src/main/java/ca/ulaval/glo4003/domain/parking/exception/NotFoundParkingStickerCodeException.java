package ca.ulaval.glo4003.domain.parking.exception;

public class NotFoundParkingStickerCodeException extends RuntimeException {
  public NotFoundParkingStickerCodeException() {
    super("Parking sticker not found");
  }
}
