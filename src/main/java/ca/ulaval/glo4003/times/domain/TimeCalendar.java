package ca.ulaval.glo4003.times.domain;

public abstract class TimeCalendar implements Comparable<TimeCalendar> {
  // TODO : #266

  public TimeCalendar(CustomDateTime customDateTime) {
    // TODO
  }

  protected abstract CustomDateTime firstDateTime();

  protected abstract CustomDateTime lastDateTime();

  public TimePeriod toPeriod() {
    // TODO
    return null;
  }
}
