package ca.ulaval.glo4003.times.helpers;

import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createLocalDateTime;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import java.time.LocalDateTime;

public class CustomDateTimeBuilder {

  private final LocalDateTime defaultDateTime = createLocalDateTime();

  private int year = defaultDateTime.getYear();
  private int month = defaultDateTime.getMonthValue();
  private int dayOfMonth = defaultDateTime.getDayOfMonth();
  private int hour = defaultDateTime.getHour();
  private int minute = defaultDateTime.getMinute();

  public static CustomDateTimeBuilder aDateTime() {
    return new CustomDateTimeBuilder();
  }

  public CustomDateTimeBuilder withDateTime(LocalDateTime dateTime) {
    this.year = dateTime.getYear();
    this.month = dateTime.getMonthValue();
    this.dayOfMonth = dateTime.getDayOfMonth();
    this.hour = dateTime.getHour();
    this.minute = dateTime.getMinute();
    return this;
  }

  public CustomDateTimeBuilder withYear(int year) {
    this.year = year;
    return this;
  }

  public CustomDateTime build() {
    LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    return new CustomDateTime(localDateTime);
  }
}
