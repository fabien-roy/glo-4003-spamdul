package ca.ulaval.glo4003.times.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTime {
  private LocalDateTime localDateTime;

  public static CustomDateTime now() {
    return new CustomDateTime(LocalDateTime.now());
  }

  private CustomDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public int getYear() {
    return localDateTime.getYear();
  }

  @Override
  public String toString() {
    return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }
}
