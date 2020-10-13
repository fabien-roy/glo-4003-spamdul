package ca.ulaval.glo4003.cars.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnumExcept;

import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
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

  public static ConsumptionTypes createConsumptionTypes() {
    return randomEnum(ConsumptionTypes.class);
  }

  public static ConsumptionTypes createNotZeroPullutionConsumptionTypes() {
    return randomEnumExcept(
        ConsumptionTypes.class, Collections.singletonList(ConsumptionTypes.ZERO_POLLUTION));
  }
}
