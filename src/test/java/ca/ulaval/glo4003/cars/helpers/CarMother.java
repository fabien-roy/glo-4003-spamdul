package ca.ulaval.glo4003.cars.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnumExcept;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import com.github.javafaker.Faker;
import java.util.Collections;

public class CarMother {
  public static String createManufacturer() {
    return Faker.instance().company().name();
  }

  public static String createModel() {
    return Faker.instance().cat().breed();
  }

  public static int createYear() {
    return Faker.instance().number().numberBetween(1960, 2010);
  }

  public static ConsumptionType createConsumptionTypes() {
    return randomEnum(ConsumptionType.class);
  }

  public static ConsumptionType createNotZeroPullutionConsumptionTypes() {
    return randomEnumExcept(
        ConsumptionType.class, Collections.singletonList(ConsumptionType.ZERO_POLLUTION));
  }
}
