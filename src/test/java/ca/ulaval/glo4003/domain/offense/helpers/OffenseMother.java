package ca.ulaval.glo4003.domain.offense.helpers;

import com.github.javafaker.Faker;

public class OffenseMother {
  public static String createReasonText() {
    return Faker.instance().superhero().descriptor();
  }

  public static String createReasonCode() {
    return Faker.instance().pokemon().name();
  }

  public static int createAmount() {
    return Faker.instance().number().numberBetween(1, 200);
  }
}
