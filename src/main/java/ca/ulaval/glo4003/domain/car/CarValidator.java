package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.car.dto.CarDTO;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidLicensePlateException;
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
      throw new InvalidLicensePlateException();
    }

    if (licensePlate.length() < 2 || licensePlate.length() > 7) {
      throw new InvalidLicensePlateException();
    }
  }

  private void validateYear(int year) {
    int currentYear = LocalDate.now().getYear();
    if (currentYear + 1 <= year) {
      throw new InvalidCarYearException(currentYear);
    }
  }
}
