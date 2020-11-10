package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CalendarHelper.*;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
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

  @Test
  public void whenComparingMonths_thenReturnDifferenceInMonths() {
    int intMonth = getTotalMonthsInteger(dateTime.toLocalDateTime());
    int otherIntMonth = getTotalMonthsInteger(otherDateTime.toLocalDateTime());
    int differenceInMonths = intMonth - otherIntMonth;

    int comparison = month.compareTo(otherMonth);

    assertThat(comparison).isEqualTo(differenceInMonths);
  }

  private LocalDateTime getMonthStart() {
    int year = dateTime.toLocalDateTime().getYear();
    int month = dateTime.toLocalDateTime().getMonthValue();
    return dateTimeAtMinimumTime(year, month, 1);
  }

  // TODO : #266 : This should be at max time
  private LocalDateTime getMonthEnd() {
    int year = dateTime.toLocalDateTime().getYear();
    int month = dateTime.toLocalDateTime().getMonthValue();
    int dayOfMonth = lastDayOfMonth(year, toJavaCalendarMonth(month));
    return dateTimeAtMaximumTime(year, month, dayOfMonth);
  }

  private int getTotalMonthsInteger(LocalDateTime dateTime) {
    return (dateTime.getYear() * 12) + dateTime.getMonthValue();
  }
}
