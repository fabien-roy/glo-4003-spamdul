package ca.ulaval.glo4003.times.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class DayMother {
  public static DayOfWeek createDay() {
    return randomEnum(DayOfWeek.class);
  }
}
