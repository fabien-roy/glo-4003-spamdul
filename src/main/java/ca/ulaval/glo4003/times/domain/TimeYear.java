package ca.ulaval.glo4003.times.domain;

import java.time.ZoneId;
import java.util.Calendar;

public class TimeYear extends TimeCalendar {

  public TimeYear(CustomDateTime customDateTime) {
    super(customDateTime);
  }

  @Override
  protected CustomDateTime firstDateTime() {
    int firstDayOfYear = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
    return thatDateTime(firstDayOfYear);
  }

  @Override
  protected CustomDateTime lastDateTime() {
    int lastDayOfYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
    return thatDateTime(lastDayOfYear);
  }

  private CustomDateTime thatDateTime(int dayOfYear) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, getYear());
    calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
    setAtMinimumTime(calendar);
    return new CustomDateTime(
        calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
  }

  @Override
  public String toString() {
    return Integer.toString(calendar.get(Calendar.YEAR));
  }

  @Override
  public int compareTo(TimeCalendar other) {
    return getYear() - other.getYear();
  }
}
