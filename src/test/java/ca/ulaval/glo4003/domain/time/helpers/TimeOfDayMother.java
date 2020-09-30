package ca.ulaval.glo4003.domain.time.helpers;

import ca.ulaval.glo4003.domain.time.TimeOfDay;
import java.time.LocalTime;

public class TimeOfDayMother {
  public static TimeOfDay createTimeOfDay() {
    return new TimeOfDay(LocalTime.parse("10:30"));
  }
}
