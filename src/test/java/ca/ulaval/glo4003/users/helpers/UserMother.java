package ca.ulaval.glo4003.users.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.times.domain.Days;
import ca.ulaval.glo4003.users.domain.Sex;
import com.github.javafaker.Faker;

public class UserMother {
  public static String createName() {
    return Faker.instance().name().firstName();
  }

  public static Sex createSex() {
    return randomEnum(Sex.class);
  }

  public static Days createAccessDay() {
    return randomEnum(Days.class);
  }
}
