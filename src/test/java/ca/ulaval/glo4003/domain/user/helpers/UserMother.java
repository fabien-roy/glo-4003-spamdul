package ca.ulaval.glo4003.domain.user.helpers;

import static ca.ulaval.glo4003.interfaces.Randomizer.randomEnum;

import ca.ulaval.glo4003.domain.user.Sex;
import com.github.javafaker.Faker;

public class UserMother {
  public static String createName() {
    return Faker.instance().name().firstName();
  }

  public static Sex createSex() {
    return randomEnum(Sex.class);
  }
}
