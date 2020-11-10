package ca.ulaval.glo4003.times.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class TimeCalendar implements Comparable<TimeCalendar> {
  protected Calendar calendar;
  protected TimePeriod period;

  public TimeCalendar(CustomDateTime dateTime) {
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

  protected abstract CustomDateTime firstDateTime();

  protected abstract CustomDateTime lastDateTime();

  public TimePeriod toPeriod() {
    return period;
  }

  protected void setAtMinimumTime(Calendar date) {
    date.set(Calendar.HOUR_OF_DAY, 0);
    date.clear(Calendar.MINUTE);
    date.clear(Calendar.SECOND);
    date.clear(Calendar.MILLISECOND);
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
