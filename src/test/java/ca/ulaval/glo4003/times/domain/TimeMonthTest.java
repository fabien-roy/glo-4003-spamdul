package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

// TODO : Fixed values would test this better (ex : tests for toString())
public class TimeMonthTest {

  private final CustomDateTime dateTime = aDateTime().build();
  private final CustomDateTime otherDateTime = aDateTime().build();

  private TimeMonth month;
  private TimeMonth otherMonth;

  @Before
  public void setUp() {
    month = new TimeMonth(dateTime);
    otherMonth = new TimeMonth(otherDateTime);
  }

  @Test
  public void whenConstructing_thenSetPeriodStartToMonthStart() {
    CustomDateTime monthStart = aDateTime().withDateTime(getMonthStart()).build();

    assertThat(month.toPeriod().getStart()).isEqualTo(monthStart);
  }

  @Test
  public void whenConstructing_thenSetPeriodEndToMonthEnd() {
    CustomDateTime monthEnd = aDateTime().withDateTime(getMonthEnd()).build();

    assertThat(month.toPeriod().getEnd()).isEqualTo(monthEnd);
  }

  @Test
  public void whenConvertingToString_thenReturnMonthInString() {
    String expectedString = dateTime.toLocalDateTime().getMonth().toString().toLowerCase();

    assertThat(month.toString()).isEqualTo(expectedString);
  }

  private LocalDateTime getMonthStart() {
    int year = dateTime.toLocalDateTime().getYear();
    int month = dateTime.toLocalDateTime().getMonthValue();
    return LocalDateTime.of(year, month, 1, 0, 0);
  }

  private LocalDateTime getMonthEnd() {
    int year = dateTime.toLocalDateTime().getYear();
    int month = dateTime.toLocalDateTime().getMonthValue();
    int dayOfMonth = getLastDayOfMonth(year, month);
    return LocalDateTime.of(year, month, dayOfMonth, 0, 0);
  }

  private int getLastDayOfMonth(int year, int month) {
    int calendarMonth = month - 1; // JavaTime to JavaCalendar
    Calendar calendar = new GregorianCalendar(year, calendarMonth, 1);
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  }
}
