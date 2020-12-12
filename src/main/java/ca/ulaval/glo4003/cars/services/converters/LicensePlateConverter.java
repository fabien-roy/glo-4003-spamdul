package ca.ulaval.glo4003.cars.services.converters;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidLicensePlateException;
import java.util.regex.Pattern;

public class LicensePlateConverter {
  private static final Pattern PATTERN = Pattern.compile("[A-Z0-9]{3}[A-Z0-9 ][A-Z0-9]{3}");
  private static final String FORMAT =
      "XXXAXXX, where X is an alphanumerical character and A is optional";

  public LicensePlate convert(String licensePlate) {
    if (licensePlate == null || !PATTERN.matcher(licensePlate).matches()) {
      throwException();
    }

    return new LicensePlate(licensePlate);
  }

  private void throwException() {
    throw new InvalidLicensePlateException(FORMAT);
  }
}
