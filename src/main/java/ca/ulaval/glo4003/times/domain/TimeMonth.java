package ca.ulaval.glo4003.times.domain;

public class TimeMonth extends TimeCalendar {
  public TimeMonth(CustomDateTime customDateTime) {
    super(customDateTime);
  }

  @Override
  protected CustomDateTime firstDateTime() {
    // TODO : #266
    return null;
  }

  @Override
  protected CustomDateTime lastDateTime() {
    // TODO : #266
    return null;
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
