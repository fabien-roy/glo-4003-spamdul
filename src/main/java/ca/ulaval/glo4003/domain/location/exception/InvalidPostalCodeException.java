package ca.ulaval.glo4003.domain.location.exception;

public class InvalidPostalCodeException extends LocationException {
  public static final String ERROR = "Invalid postal parkingStickerCode";
  public static final String DESCRIPTION = "Postal parkingStickerCode is invalid";

  public InvalidPostalCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
