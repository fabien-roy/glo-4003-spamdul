package ca.ulaval.glo4003.domain.user.helpers;

import com.github.javafaker.Faker;

public class UserMother {
  public static String createName() {
    return Faker.instance().name().firstName();
  }
}
