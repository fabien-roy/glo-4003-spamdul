package ca.ulaval.glo4003.times.domain;

import java.time.ZoneId;
import java.util.Calendar;

public class TimeDay extends TimeCalendar {
  public TimeDay(CustomDateTime customDateTime) {
    super(customDateTime);
  }

  @Override
  protected CustomDateTime firstDateTime() {
    return thatDate();
  }

  @Override
  protected CustomDateTime lastDateTime() {
    // TODO : #266
    return null;
  }

  private CustomDateTime thatDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, getYear());
    calendar.set(Calendar.DAY_OF_YEAR, getDayOfYear());
    setAtMinimumTime(calendar);
    return new CustomDateTime(
        calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
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
