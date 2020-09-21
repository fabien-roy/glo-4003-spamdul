package ca.ulaval.glo4003.domain.parking;

public class InvalidReceptionMethodException extends RuntimeException {
  public InvalidReceptionMethodException() {
    super("Invalid reception method");
  }
}
