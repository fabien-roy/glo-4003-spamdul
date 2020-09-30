package ca.ulaval.glo4003.times.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.times.domain.Days;

public class DayMother {
  public static Days createDay() {
    return randomEnum(Days.class);
  }
}
