package ca.ulaval.glo4003.cars.services.converters;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidLicensePlateException;
import java.util.regex.Pattern;

public class LicensePlateConverter {
  private static final Pattern PATTERN = Pattern.compile("[A-Z0-9]{3}[A-Z0-9 ][A-Z0-9]{3}");

  public LicensePlate convert(String licensePlate) {
    if (licensePlate == null) {
      throw new InvalidLicensePlateException();
    } else if (licensePlate.length() < 2 || licensePlate.length() > 7) {
      throw new InvalidLicensePlateException();
    } else if (!PATTERN.matcher(licensePlate).matches()) {
      throw new InvalidLicensePlateException();
    }

    return new LicensePlate(licensePlate);
  }
}
