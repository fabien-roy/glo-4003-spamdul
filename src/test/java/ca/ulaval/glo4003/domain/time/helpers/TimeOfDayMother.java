package ca.ulaval.glo4003.domain.time.helpers;

import ca.ulaval.glo4003.domain.time.TimeOfDay;

public class TimeOfDayMother {
  public static TimeOfDay createTimeOfDay() {
    return new TimeOfDay("10:30");
  }
}
