package ca.ulaval.glo4003.times.domain;

public class TimeMonth extends TimeCalendar {
  // TODO : #266

  public TimeMonth(CustomDateTime customDateTime) {
    super(customDateTime);
  }

  @Override
  protected CustomDateTime firstDateTime() {
    // TODO
    return null;
  }

  @Override
  protected CustomDateTime lastDateTime() {
    // TODO
    return null;
  }

  @Override
  public String toString() {
    // TODO
    return "";
  }

  @Override
  public int compareTo(TimeCalendar other) {
    // TODO
    return 1;
  }
}
