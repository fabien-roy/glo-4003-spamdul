package ca.ulaval.glo4003.domain.parking.exception;

public class MissingEmailException extends ParkingException {
  private static final String ERROR = "Missing property : email";
  private static final String DESCRIPTION = "Property email is missing";

  public MissingEmailException() {
    super(ERROR, DESCRIPTION);
  }
}
