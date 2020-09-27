package ca.ulaval.glo4003.domain.parking.exception;

public class AccessNotAllowedException extends ParkingException {
  private static final String ERROR = " Access not allowed";
  private static final String DESCRIPTION = "Parking sticker day does not correspond";

  public AccessNotAllowedException() {
    super(ERROR, DESCRIPTION);
  }
}
