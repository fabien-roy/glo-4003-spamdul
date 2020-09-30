package ca.ulaval.glo4003.cars.assemblers;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.InvalidLicensePlateException;
import java.util.regex.Pattern;

public class LicensePlateAssembler {
  private static final Pattern PATTERN = Pattern.compile("[A-Z0-9]{3}[A-Z0-9 ][A-Z0-9]{3}");

  public LicensePlate assemble(String licensePlate) {
    if (licensePlate.length() < 2 || licensePlate.length() > 7) {
      throw new InvalidLicensePlateException();
    } else if (!PATTERN.matcher(licensePlate).matches()) {
      throw new InvalidLicensePlateException();
    }

    return new LicensePlate(licensePlate);
  }
}
