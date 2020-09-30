package ca.ulaval.glo4003.domain.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TimeOfDay {
  private final LocalTime localTime;

  public TimeOfDay(LocalTime localTime) {
    // String format is hh:mm:ss
    this.localTime = localTime;
  }

  public String toString() {
    return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TimeOfDay timeOfDay = (TimeOfDay) o;
    return localTime.equals(timeOfDay.localTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localTime);
  }
}
