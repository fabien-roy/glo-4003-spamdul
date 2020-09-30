package ca.ulaval.glo4003.domain.offense.helpers;

import static ca.ulaval.glo4003.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.domain.offense.OffenseCodes;
import com.github.javafaker.Faker;

public class OffenseMother {
  public static String createReasonText() {
    return Faker.instance().superhero().descriptor();
  }

  public static OffenseCodes createReasonCode() {
    return randomEnum(OffenseCodes.class);
  }

  public static double createAmount() {
    return Faker.instance().number().numberBetween(1, 200);
  }
}
