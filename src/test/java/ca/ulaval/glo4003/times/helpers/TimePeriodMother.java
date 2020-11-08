package ca.ulaval.glo4003.times.helpers;

import com.github.javafaker.Faker;

public class TimePeriodMother {
  public static int createAmountOfDays() {
    return Faker.instance().number().numberBetween(2, 10);
  }
}
