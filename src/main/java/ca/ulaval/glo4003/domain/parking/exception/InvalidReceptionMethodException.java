package ca.ulaval.glo4003.domain.parking.exception;

public class InvalidReceptionMethodException extends ParkingException {
  private static final String ERROR = "Invalid reception method";
  private static final String DESCRIPTION = "Reception method is invalid";

  public InvalidReceptionMethodException() {
    super(ERROR, DESCRIPTION);
  }
}
