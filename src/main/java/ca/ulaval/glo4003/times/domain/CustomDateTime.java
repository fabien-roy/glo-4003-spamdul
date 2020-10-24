package ca.ulaval.glo4003.times.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTime {
  private LocalDateTime localDateTime;

  public CustomDateTime() {
    this.localDateTime = LocalDateTime.now();
  }

  public int getYear() {
    return localDateTime.getYear();
  }

  @Override
  public String toString() {
    return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }
}
