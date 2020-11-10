package ca.ulaval.glo4003.times.domain;

import java.util.Calendar;

public class TimeDay extends TimeCalendar {
  public TimeDay(CustomDateTime customDateTime) {
    super(customDateTime);
  }

  @Override
  protected CustomDateTime firstDateTime() {
    Calendar calendar = thatDay();
    setAtMinimumTime(calendar);
    return toDateTime(calendar);
  }

  @Override
  protected CustomDateTime lastDateTime() {
    Calendar calendar = thatDay();
    setAtMaximumTime(calendar);
    return toDateTime(calendar);
  }

  private Calendar thatDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, getYear());
    calendar.set(Calendar.DAY_OF_YEAR, getDayOfYear());
    return calendar;
  }

  @Override
  public String toString() {
    // TODO : #266
    return "";
  }

  @Override
  public int compareTo(TimeCalendar other) {
    // TODO : #266
    return 1;
  }
}
