package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CalendarHelper.dateTimeAtMaximumTime;
import static ca.ulaval.glo4003.times.helpers.CalendarHelper.dateTimeAtMinimumTime;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

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
  public void whenGettingPeriodStart_thenGetYearStart() {
    CustomDateTime expectedYearStart = aDateTime().withDateTime(getYearStart()).build();

    CustomDateTime yearStart = year.toPeriod().getStart();

    assertThat(yearStart).isEqualTo(expectedYearStart);
  }

  @Test
  public void givenConstructionWithYear_whenGettingPeriodStart_thenGetYearStart() {
    CustomDateTime expectedYearStart = aDateTime().withDateTime(getYearStart()).build();

    TimeYear year = new TimeYear(dateTime.toLocalDateTime().getYear());
    CustomDateTime yearStart = year.toPeriod().getStart();

    assertThat(yearStart).isEqualTo(expectedYearStart);
  }

  @Test
  public void whenGettingPeriodEnd_thenGetYearEnd() {
    CustomDateTime expectedYearEnd = aDateTime().withDateTime(getYearEnd()).build();

    CustomDateTime yearEnd = year.toPeriod().getEnd();

    assertThat(yearEnd).isEqualTo(expectedYearEnd);
  }

  @Test
  public void givenConstructionWithYear_whenGettingPeriodEnd_thenGetYearEnd() {
    CustomDateTime expectedYearEnd = aDateTime().withDateTime(getYearEnd()).build();

    TimeYear year = new TimeYear(dateTime.toLocalDateTime().getYear());
    CustomDateTime yearEnd = year.toPeriod().getEnd();

    assertThat(yearEnd).isEqualTo(expectedYearEnd);
  }

  // TODO : Fixed values would test this better.
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
