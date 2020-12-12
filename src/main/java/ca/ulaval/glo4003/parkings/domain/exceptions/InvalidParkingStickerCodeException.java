package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidParkingStickerCodeException extends ApplicationException {
  private static final String ERROR = "Invalid parking sticker code";
  private static final String DESCRIPTION = "Parking sticker code is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidParkingStickerCodeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
