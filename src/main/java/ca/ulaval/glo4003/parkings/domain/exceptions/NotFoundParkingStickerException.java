package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;

public class NotFoundParkingStickerException extends ApplicationException {
  private static final String ERROR = "Parking sticker not found";
  private static final String DESCRIPTION = "Parking sticker with code %s was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private final ParkingStickerCode parkingStickerCode;

  public NotFoundParkingStickerException(ParkingStickerCode parkingStickerCode) {
    super(ERROR, DESCRIPTION, CODE);
    this.parkingStickerCode = parkingStickerCode;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, parkingStickerCode.toString());
  }
}
