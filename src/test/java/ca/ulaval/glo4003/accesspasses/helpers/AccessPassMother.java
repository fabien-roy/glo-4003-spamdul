package ca.ulaval.glo4003.accesspasses.helpers;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import com.github.javafaker.Faker;

public class AccessPassMother {
  public static AccessPassCode createAccessPassCode() {
    return new AccessPassCode(Faker.instance().color().toString());
  }
}
