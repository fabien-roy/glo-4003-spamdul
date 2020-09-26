package ca.ulaval.glo4003.domain.parking.exception;

public class InvalidParkingStickerDayException extends RuntimeException {
  public InvalidParkingStickerDayException() {
    super("Invalid parking sticker day");
  }
}
