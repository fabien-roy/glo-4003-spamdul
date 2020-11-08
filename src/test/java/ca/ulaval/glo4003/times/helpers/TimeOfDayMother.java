package ca.ulaval.glo4003.times.helpers;

import ca.ulaval.glo4003.times.domain.TimeOfDay;
import java.time.LocalTime;

public class TimeOfDayMother {
  public static TimeOfDay createTimeOfDay() {
    // TODO : This isn't random at all.
    return new TimeOfDay(LocalTime.parse("10:30"));
  }
}
