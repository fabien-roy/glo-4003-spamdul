package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class TimeCalendarTest {

  private final CustomDateTime dateTime = aDateTime().build();

  private TimeCalendar calendar;

  @Test
  public void whenGettingYear_thenGetYear() {
    calendar = new TimeYear(dateTime);

    int year = calendar.getYear();

    assertThat(year).isEqualTo(dateTime.toLocalDateTime().getYear());
  }

  @Test
  public void whenGettingMonth_thenGetMonth() {
    calendar = new TimeMonth(dateTime);
    int expectedMonth = dateTime.toLocalDateTime().getMonthValue() - 1; // JavaTime to JavaCalendar

    int month = calendar.getMonth();

    assertThat(month).isEqualTo(expectedMonth);
  }

  @Test
  public void whenGettingYearMonth_thenGetYearMonth() {
    calendar = new TimeMonth(dateTime);
    int expectedYear = dateTime.toLocalDateTime().getYear();
    int expectedMonth = dateTime.toLocalDateTime().getMonthValue() - 1; // JavaTime to JavaCalendar
    int expectedYearMonth = expectedYear * 12 + expectedMonth;

    int yearMonth = calendar.getYearMonth();

    assertThat(yearMonth).isEqualTo(expectedYearMonth);
  }
}
