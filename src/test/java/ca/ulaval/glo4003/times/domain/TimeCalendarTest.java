package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class TimeCalendarTest {

  private CustomDateTime dateTime = aDateTime().build();

  private TimeCalendar calendar;

  @Test
  public void whenGettingYear_thenGetYear() {
    calendar = new TimeYear(dateTime);

    int year = calendar.getYear();

    assertThat(year).isEqualTo(dateTime.toLocalDateTime().getYear());
  }
}
