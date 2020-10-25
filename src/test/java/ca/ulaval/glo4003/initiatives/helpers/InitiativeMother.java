package ca.ulaval.glo4003.initiatives.helpers;

import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import com.github.javafaker.Faker;

public class InitiativeMother {
  public static String createInitiativeName() {
    return Faker.instance().name().title();
  }

  public static InitiativeCode createInitiativeCode() {
    return new InitiativeCode(Faker.instance().color().toString());
  }
}
