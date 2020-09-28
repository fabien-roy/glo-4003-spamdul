package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.domain.car.exceptions.InvalidLicensePlateException;
import java.util.regex.Pattern;

public class LicensePlateAssembler {
  private static final Pattern PATTERN = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

  public LicensePlate assemble(String licensePlate) {
    if (licensePlate.length() < 2 || licensePlate.length() > 7) {
      throw new InvalidLicensePlateException();
    }
    if (PATTERN.matcher(licensePlate).matches()) {
      throw new InvalidLicensePlateException();
    }

    return new LicensePlate(licensePlate);
  }
}
