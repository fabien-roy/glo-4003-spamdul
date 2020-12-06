package ca.ulaval.glo4003.cars.helpers;


import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnumExcept;

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

  public static ConsumptionType createConsumptionType() {
    return randomEnum(ConsumptionType.class);
  }

  public static ConsumptionType createConsumptionTypeOtherThanZeroPollution() {
    return randomEnumExcept(
        ConsumptionType.class, Collections.singletonList(ConsumptionType.ZERO_POLLUTION));
  }

  // TODO : Move this to builder's buildMany()
  public static List<Car> createCars() {
    List<Car> cars = new ArrayList<>();
    cars.add(aCar().build());

    return cars;
  }
}
