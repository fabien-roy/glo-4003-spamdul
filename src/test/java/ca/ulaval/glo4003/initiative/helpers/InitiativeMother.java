package ca.ulaval.glo4003.initiative.helpers;

import ca.ulaval.glo4003.initiative.domain.InitiativeCode;
import com.github.javafaker.Faker;

public class InitiativeMother {
  public static String createName() {
    return Faker.instance().name().title();
  }

  public static Double createAmount() {
    return Double.valueOf(Faker.instance().number().numberBetween(1, 200));
  }

  public static InitiativeCode createCode() {
    return new InitiativeCode(Faker.instance().color().toString());
  }
}
