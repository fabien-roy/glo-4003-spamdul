package ca.ulaval.glo4003.times.domain;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Locale;

public class TimeMonth extends TimeCalendar {
  public TimeMonth(CustomDateTime customDateTime) {
    super(customDateTime);
  }

  @Override
  protected CustomDateTime firstDateTime() {
    int firstDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
    return thatDate(firstDayOfMonth);
  }

  @Override
  protected CustomDateTime lastDateTime() {
    int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    return thatDate(lastDayOfMonth);
  }

  private CustomDateTime thatDate(int dayOfMonth) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, getYear());
    calendar.set(Calendar.MONTH, getMonth());
    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    setAtMinimumTime(calendar);
    return new CustomDateTime(
        calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
  }

  @Override
  public String toString() {
    return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US).toLowerCase();
  }

  @Override
  public int compareTo(TimeCalendar other) {
    return getYearMonth() - other.getYearMonth();
  }
}
