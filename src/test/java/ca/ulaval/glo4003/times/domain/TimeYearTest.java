package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CalendarHelper.dateTimeAtMaximumTime;
import static ca.ulaval.glo4003.times.helpers.CalendarHelper.dateTimeAtMinimumTime;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

// TODO : Fixed values would test this better (ex : tests for toString())
public class TimeYearTest {

  private final CustomDateTime dateTime = aDateTime().build();
  private final CustomDateTime otherDateTime = aDateTime().build();

  private TimeYear year;
  private TimeYear otherYear;

  @Before
  public void setUp() {
    year = new TimeYear(dateTime);
    otherYear = new TimeYear(otherDateTime);
  }

  @Test
  public void whenConstructing_thenSetPeriodStartToYearStart() {
    CustomDateTime yearStart = aDateTime().withDateTime(getYearStart()).build();

    assertThat(year.toPeriod().getStart()).isEqualTo(yearStart);
  }

  @Test
  public void whenConstructing_thenSetPeriodEndToYearEnd() {
    CustomDateTime yearEnd = aDateTime().withDateTime(getYearEnd()).build();

    assertThat(year.toPeriod().getEnd()).isEqualTo(yearEnd);
  }

  @Test
  public void whenConvertingToString_thenReturnYearAsString() {
    String expectedString = Integer.toString(dateTime.toLocalDateTime().getYear());

    assertThat(year.toString()).isEqualTo(expectedString);
  }

  @Test
  public void whenComparingYears_thenReturnDifferenceInYears() {
    int intYear = dateTime.toLocalDateTime().getYear();
    int otherIntYear = otherDateTime.toLocalDateTime().getYear();
    int differenceInYears = intYear - otherIntYear;

    int comparison = year.compareTo(otherYear);

    assertThat(comparison).isEqualTo(differenceInYears);
  }

  private LocalDateTime getYearStart() {
    int year = dateTime.toLocalDateTime().getYear();
    return dateTimeAtMinimumTime(year, 1, 1);
  }

  private LocalDateTime getYearEnd() {
    int year = dateTime.toLocalDateTime().getYear();
    return dateTimeAtMaximumTime(year, 12, 31);
  }
}
