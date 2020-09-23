package ca.ulaval.glo4003.domain.parking.exception;

public class InvalidReceptionMethodException extends RuntimeException {
  public InvalidReceptionMethodException() {
    super("Invalid reception method");
  }
}
