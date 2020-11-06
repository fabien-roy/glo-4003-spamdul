package ca.ulaval.glo4003.times.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTime {
  private final LocalDateTime localDateTime;

  public CustomDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public static CustomDateTime now() {
    return new CustomDateTime(LocalDateTime.now());
  }

  // TODO : Pretty sure this will be useless when bill reporting will be refactored.
  public int getYear() {
    return localDateTime.getYear();
  }

  @Override
  public String toString() {
    return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }
}
