package ca.ulaval.glo4003.offenses.helpers;

import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import com.github.javafaker.Faker;

public class OffenseTypeMother {
  public static String createDescription() {
    return Faker.instance().superhero().descriptor();
  }

  public static OffenseCode createOffenseCode() {
    String code = Faker.instance().color().toString().toUpperCase();
    return new OffenseCode(code);
  }
}
