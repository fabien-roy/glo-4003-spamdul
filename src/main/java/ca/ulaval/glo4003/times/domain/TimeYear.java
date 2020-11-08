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
    Calendar date = Calendar.getInstance();
    date.set(Calendar.YEAR, getYear());
    date.set(Calendar.DAY_OF_YEAR, dayOfYear);
    setAtMidnight(date);
    return new CustomDateTime(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
  }

  @Override
  public String toString() {
    // TODO : #266
    return "";
  }

  @Override
  public int compareTo(TimeCalendar other) {
    return getYear() - other.getYear();
  }
}
