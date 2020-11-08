package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.TimePeriodBuilder.aTimePeriod;
import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import org.junit.Test;

// TODO : Refactor this test class to use random values
public class TimePeriodTest {

  private static final int firstYear = 2020;
  private static final int secondYear = 2021;

  @Test
  public void givenSingleYearPeriod_whenGettingYears_thenGetYears() {
    TimePeriod singleYearPeriod = aTimePeriod().withYears(firstYear, firstYear).build();

    List<TimeCalendar> years = singleYearPeriod.getYears();

    assertThat(years).hasSize(1);
    assertThat(years.get(0).getYear()).isEqualTo(firstYear);
  }

  @Test
  public void getYears_withMultipleYearsPeriod_shouldGetYears() {
    TimePeriod multipleYearsPeriod = aTimePeriod().withYears(firstYear, secondYear).build();

    List<TimeCalendar> years = multipleYearsPeriod.getYears();

    assertThat(years).hasSize(2);
    assertThat(years.get(0).getYear()).isEqualTo(firstYear);
    assertThat(years.get(1).getYear()).isEqualTo(secondYear);
  }
}
