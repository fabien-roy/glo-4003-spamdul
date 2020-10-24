package ca.ulaval.glo4003.cars.helpers;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;

public class LicensePlateMother {
  private static final String PATTERN = "[A-Z0-9]{3}[A-Z0-9 ][A-Z0-9]{3}";

  public static LicensePlate createLicensePlate() {
    String licensePlate = Faker.instance().regexify(PATTERN);
    return new LicensePlate(licensePlate);
  }

  public static List<LicensePlate> createLicensesPlate() {
    List<LicensePlate> licensePlates = new ArrayList<>();
    licensePlates.add(createLicensePlate());

    return licensePlates;
  }
}
