package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.car.dto.CarDTO;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidLicenseNumberException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class CarValidator {

  public CarValidator() {}

  public void validate(CarDTO carDTO) {
    validateLicensePlate(carDTO.getLicensePlate());
    validateYear(carDTO.getYear());
  }

  private void validateLicensePlate(String licensePlate) {
    Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

    if (regex.matcher(licensePlate).find()) {
      throw new InvalidLicenseNumberException(
          "Invalid plate number", "Must not contain special characters");
    }

    if (licensePlate.length() < 2 || licensePlate.length() > 7) {
      throw new InvalidLicenseNumberException(
          "Invalid plate number", "Must be between 2 and 7 characters");
    }
  }

  private void validateYear(int year) {
    int currentYear = LocalDate.now().getYear();
    if (currentYear + 1 <= year) {
      String errorDescription = String.format("Must be before %d", currentYear);
      throw new InvalidCarYearException("Year of model is invalid", errorDescription);
    }
  }
}
