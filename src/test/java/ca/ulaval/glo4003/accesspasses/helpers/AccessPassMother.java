package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import com.github.javafaker.Faker;

public class AccessPassMother {
  public static AccessPassCode createAccessPassCode() {
    return new AccessPassCode(Faker.instance().color().toString());
  }

  public static AccessPeriod createAccessPeriod() {
    return randomEnum(AccessPeriod.class);
  }
}
