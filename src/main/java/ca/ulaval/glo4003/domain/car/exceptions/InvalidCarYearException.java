package ca.ulaval.glo4003.domain.car.exceptions;

public class InvalidCarYearException extends InvalidCarException {
  public InvalidCarYearException(String error, String description) {
    super(error, description);
  }
}
