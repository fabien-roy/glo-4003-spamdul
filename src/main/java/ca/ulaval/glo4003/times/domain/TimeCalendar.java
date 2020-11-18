package ca.ulaval.glo4003.times.domain;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class TimeCalendar implements Comparable<TimeCalendar> {
  protected Calendar calendar;
  protected TimePeriod period;

  public TimeCalendar(CustomDateTime dateTime) {
    setUpCustomDateTime(dateTime);
  }

  public TimeCalendar(String monthName) {
    LocalDateTime localDateTime = LocalDateTime.now();

    if (!monthName.equals("null")) {
      localDateTime = localDateTime.withMonth(Month.valueOf(monthName.toUpperCase()).getValue());
    }

    CustomDateTime customDate = new CustomDateTime(localDateTime);
    setUpCustomDateTime(customDate);
  }

  public void setUpCustomDateTime(CustomDateTime dateTime) {
    calendar = GregorianCalendar.from(dateTime.toZonedDateTime());
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    period = new TimePeriod(firstDateTime(), lastDateTime());
  }

  protected int getYear() {
    return calendar.get(Calendar.YEAR);
  }

  protected int getMonth() {
    return calendar.get(Calendar.MONTH);
  }

  protected int getYearMonth() {
    return getYear() * 12 + getMonth();
  }

  protected int getDayOfYear() {
    return calendar.get(Calendar.DAY_OF_YEAR);
  }

  protected CustomDateTime toDateTime(Calendar calendar) {
    return new CustomDateTime(
        calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
  }

  protected Calendar toCalendar() {
    return calendar;
  }

  protected abstract CustomDateTime firstDateTime();

  protected abstract CustomDateTime lastDateTime();

  public TimePeriod toPeriod() {
    return period;
  }

  protected void setAtMinimumTime(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.clear(Calendar.MINUTE);
    calendar.clear(Calendar.SECOND);
    calendar.clear(Calendar.MILLISECOND);
  }

  protected void setAtMaximumTime(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeCalendar other = (TimeCalendar) object;

    return toPeriod().equals(other.toPeriod());
  }

  @Override
  public int hashCode() {
    return period.hashCode();
  }
}
