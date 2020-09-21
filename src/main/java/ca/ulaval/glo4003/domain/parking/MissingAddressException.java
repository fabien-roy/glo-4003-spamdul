package ca.ulaval.glo4003.domain.parking;

public class MissingAddressException extends RuntimeException {
  public MissingAddressException() {
    super("Missing property : address");
  }
}
