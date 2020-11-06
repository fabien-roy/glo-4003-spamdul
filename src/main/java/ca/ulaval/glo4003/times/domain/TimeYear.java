package ca.ulaval.glo4003.times.domain;

public class TimeYear extends TimeCalendar {
  // TODO : #248

  public TimeYear(CustomDateTime customDateTime) {
    super(customDateTime);
  }

  @Override
  protected CustomDate firstDate() {
    // TODO
    return null;
  }

  @Override
  protected CustomDate lastDate() {
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
