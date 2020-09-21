package ca.ulaval.glo4003.domain.car;

public class InvalidCarException extends RuntimeException {

  public InvalidCarException(String message) {
    super(message);
  }
}
