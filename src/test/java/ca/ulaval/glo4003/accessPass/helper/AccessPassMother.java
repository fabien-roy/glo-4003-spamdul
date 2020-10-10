package ca.ulaval.glo4003.accessPass.helper;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.times.domain.Days;

public class AccessPassMother {
  public static Days createDay() {
    return randomEnum(Days.class);
  }
}
