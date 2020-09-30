package ca.ulaval.glo4003.domain.time.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.domain.time.Days;

public class DayMother {
  public static Days createDay() {
    return randomEnum(Days.class);
  }
}
