package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundParkingStickerException extends ApplicationException {
  private static final String ERROR = "Parking sticker not found";
  private static final String DESCRIPTION = "Parking sticker was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public NotFoundParkingStickerException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
