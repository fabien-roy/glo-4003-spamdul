package ca.ulaval.glo4003.domain.parking;

public class UnwantedAddressException extends RuntimeException {
  public UnwantedAddressException() {
    super("Unwanted property : address");
  }
}
