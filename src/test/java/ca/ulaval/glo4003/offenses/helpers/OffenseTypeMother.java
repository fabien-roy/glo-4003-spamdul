package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import com.github.javafaker.Faker;

public class OffenseTypeMother {
  public static String createDescription() {
    return Faker.instance().superhero().descriptor();
  }

  public static OffenseCodes createOffenseCode() {
    return randomEnum(OffenseCodes.class);
  }

  public static double createAmount() {
    return Faker.instance().number().numberBetween(1, 200);
  }
}
