package ca.ulaval.glo4003.domain.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TimeOfDay {
  private final LocalTime localTime;

  public TimeOfDay(String time) {
    // String format is hh:mm:ss
    this.localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
  }

  public String toString() {
    return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    TimeOfDay timeOfDay = (TimeOfDay) object;
    return localTime.equals(timeOfDay.localTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localTime);
  }
}
