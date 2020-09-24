package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.car.dto.CarDTO;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarException;
import java.time.LocalDate;

public class CarValidator {

  public CarValidator() {}

  public void validate(CarDTO carDTO) {
    validateLicensePlate(carDTO.getLicensePlate());
    validateYear(carDTO.getYear());
  }

  private void validateLicensePlate(String licensePlate) {
    // TODO : Validate that the plate contains no illegal characters
    if (licensePlate.length() < 2 || licensePlate.length() > 7) {
      throw new InvalidCarException("Invalid plate number", "Must be between 2 and 7 characters");
    }
  }

  private void validateYear(int year) {
    int currentYear = LocalDate.now().getYear();
    if (currentYear + 1 <= year) {
      // TODO : validate that it works
      String errorDescription = String.format("Must be before %s %d", currentYear);
      throw new InvalidCarException("Year of model is invalid", errorDescription);
    }
  }
}
