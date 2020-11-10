package ca.ulaval.glo4003.times.domain;

import java.util.Calendar;
import java.util.Locale;

public class TimeMonth extends TimeCalendar {
  public TimeMonth(CustomDateTime customDateTime) {
    super(customDateTime);
  }

  @Override
  protected CustomDateTime firstDateTime() {
    Calendar month = thatMonth();
    int firstDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
    month.set(Calendar.DAY_OF_MONTH, firstDayOfMonth);
    setAtMinimumTime(month);
    return toDateTime(month);
  }

  @Override
  protected CustomDateTime lastDateTime() {
    Calendar month = thatMonth();
    int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    month.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
    setAtMaximumTime(month);
    return toDateTime(month);
  }

  private Calendar thatMonth() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, getYear());
    calendar.set(Calendar.MONTH, getMonth());
    return calendar;
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
