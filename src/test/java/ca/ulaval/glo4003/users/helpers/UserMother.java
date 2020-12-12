package ca.ulaval.glo4003.users.helpers;

import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.users.domain.Sex;
import com.github.javafaker.Faker;

public class UserMother {
  public static String createName() {
    return Faker.instance().name().firstName();
  }

  public static Sex createSex() {
    return randomEnum(Sex.class);
  }
}
