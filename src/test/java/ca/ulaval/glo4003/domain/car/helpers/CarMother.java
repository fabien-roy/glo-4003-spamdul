package ca.ulaval.glo4003.domain.car.helpers;

import com.github.javafaker.Faker;

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
}
