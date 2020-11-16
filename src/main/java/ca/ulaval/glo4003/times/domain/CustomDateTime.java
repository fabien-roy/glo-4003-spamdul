package ca.ulaval.glo4003.times.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTime {
  private final LocalDateTime dateTime;

  public CustomDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public static CustomDateTime now() {
    return new CustomDateTime(LocalDateTime.now());
  }

  public TimeYear getYear() {
    return new TimeYear(this);
  }

  public TimeMonth getMonth() {
    return new TimeMonth(this);
  }

  public TimeDay getDay() {
    return new TimeDay(this);
  }

  public CustomDateTime plusDays(int days) {
    return new CustomDateTime(dateTime.plusDays(days));
  }

  public CustomDateTime minusDays(int days) {
    return new CustomDateTime(dateTime.minusDays(days));
  }

  public boolean isBefore(CustomDateTime other) {
    return dateTime.isBefore(other.toLocalDateTime());
  }

  public boolean isAfter(CustomDateTime other) {
    return dateTime.isAfter(other.toLocalDateTime());
  }

  // TODO : Pretty sure this will be useless when bill reporting will be refactored.
  @Deprecated
  public int getIntYear() {
    return dateTime.getYear();
  }

  @Override
  public String toString() {
    return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }

  public LocalDateTime toLocalDateTime() {
    return dateTime;
  }

  public ZonedDateTime toZonedDateTime() {
    return dateTime.atZone(ZoneId.systemDefault());
  }

  public CustomDate toDate() {
    return new CustomDate(dateTime.toLocalDate());
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    CustomDateTime otherDateTime = (CustomDateTime) object;

    return dateTime.equals(otherDateTime.toLocalDateTime());
  }

  @Override
  public int hashCode() {
    return dateTime.hashCode();
  }
}
