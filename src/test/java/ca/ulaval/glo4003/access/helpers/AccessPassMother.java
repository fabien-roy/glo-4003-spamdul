package ca.ulaval.glo4003.access.helpers;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import com.github.javafaker.Faker;

public class AccessPassMother {
  public static AccessPassCode createAccessPassCode() {
    return new AccessPassCode(Faker.instance().color().toString());
  }
}
