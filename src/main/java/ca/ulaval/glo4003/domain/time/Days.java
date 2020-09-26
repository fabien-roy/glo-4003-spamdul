package ca.ulaval.glo4003.domain.time;

import ca.ulaval.glo4003.domain.time.exception.InvalidDayException;
import java.util.HashMap;
import java.util.Map;

public enum Days {
  MONDAY("monday"),
  TUESDAY("tuesday"),
  WEDNESDAY("wednesday"),
  THURSDAY("thursday"),
  FRIDAY("friday"),
  SATURDAY("saturday"),
  SUNDAY("sunday");

  private String day;
  private static final Map<String, Days> lookup = new HashMap<>();

  static {
    for (Days day : Days.values()) {
      lookup.put(day.toString(), day);
    }
  }

  Days(String day) {
    this.day = day;
  }

  @Override
  public String toString() {
    return day;
  }

  public static Days get(String type) {
    type = type.toLowerCase();
    Days foundDay = lookup.get(type);

    if (foundDay == null) throw new InvalidDayException();

    return foundDay;
  }

  public static Days getToday(String dayOfWeek) {
    return MONDAY;
  }
}
