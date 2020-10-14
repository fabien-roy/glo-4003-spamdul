package ca.ulaval.glo4003.times.domain;

import ca.ulaval.glo4003.times.exceptions.InvalidDayOfWeekException;
import java.util.HashMap;
import java.util.Map;

public enum DayOfWeek {
  MONDAY("monday"),
  TUESDAY("tuesday"),
  WEDNESDAY("wednesday"),
  THURSDAY("thursday"),
  FRIDAY("friday"),
  SATURDAY("saturday"),
  SUNDAY("sunday");

  private final String day;
  private static final Map<String, DayOfWeek> lookup = new HashMap<>();

  static {
    for (DayOfWeek day : DayOfWeek.values()) {
      lookup.put(day.toString(), day);
    }
  }

  DayOfWeek(String day) {
    this.day = day;
  }

  @Override
  public String toString() {
    return day;
  }

  public static DayOfWeek get(String day) {
    if (day == null) throw new InvalidDayOfWeekException();

    DayOfWeek foundDay = lookup.get(day.toLowerCase());

    if (foundDay == null) throw new InvalidDayOfWeekException();

    return foundDay;
  }
}
