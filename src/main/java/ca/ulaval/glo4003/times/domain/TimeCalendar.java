package ca.ulaval.glo4003.times.domain;

public abstract class TimeCalendar implements Comparable<TimeCalendar> {
  // TODO : #248

  public TimeCalendar(CustomDateTime customDateTime) {
    // TODO
  }

  protected abstract CustomDate firstDate();

  protected abstract CustomDate lastDate();

  public TimePeriod toPeriod() {
    // TODO
    return null;
  }
}
