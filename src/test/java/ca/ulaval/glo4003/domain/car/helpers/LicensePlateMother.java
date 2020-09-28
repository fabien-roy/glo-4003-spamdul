package ca.ulaval.glo4003.domain.car.helpers;

import ca.ulaval.glo4003.domain.car.LicensePlate;
import com.github.javafaker.Faker;

public class LicensePlateMother {
  private static final String PATTERN = "[A-Z0-9]{3}[A-Z0-9 ][A-Z0-9]{3}";

  public static LicensePlate createLicensePlate() {
    String licensePlate = Faker.instance().regexify(PATTERN);
    return new LicensePlate(licensePlate);
  }
}
