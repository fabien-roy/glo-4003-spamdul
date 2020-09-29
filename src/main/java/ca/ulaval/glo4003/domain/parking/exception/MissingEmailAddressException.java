package ca.ulaval.glo4003.domain.parking.exception;

public class MissingEmailAddressException extends ParkingException {
  private static final String ERROR = "Missing property : emailAddress";
  private static final String DESCRIPTION = "Property emailAddress is missing";

  public MissingEmailAddressException() {
    super(ERROR, DESCRIPTION);
  }
}
