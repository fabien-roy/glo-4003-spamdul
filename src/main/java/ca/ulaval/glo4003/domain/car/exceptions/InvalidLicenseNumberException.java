package ca.ulaval.glo4003.domain.car.exceptions;

public class InvalidLicenseNumberException extends CarException {
  public InvalidLicenseNumberException(String error, String description) {
    super(error, description);
  }
}
