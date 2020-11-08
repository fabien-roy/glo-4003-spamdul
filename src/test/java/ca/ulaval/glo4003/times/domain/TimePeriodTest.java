package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CalendarHelper.toJavaCalendarMonth;
import static ca.ulaval.glo4003.times.helpers.TimePeriodBuilder.aTimePeriod;
import static ca.ulaval.glo4003.times.helpers.TimePeriodMother.createAmountOfDays;
import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

// TODO : Refactor this test class to use random values
public class TimePeriodTest {

  private static final int FIRST_YEAR = 2020;
  private static final int SECOND_YEAR = 2021;
  private static final int FIRST_MONTH = 1;
  private static final int SECOND_MONTH = 2;

  private TimePeriod period;

  private final int amountOfDays = createAmountOfDays();
  private final CustomDateTime start = CustomDateTime.now();
  private final CustomDateTime end = start.plusDays(amountOfDays);

  @Before
  public void setUp() {
    period = new TimePeriod(start, end);
  }

  @Test
  public void givenSingleYearPeriod_whenGettingYears_thenGetYears() {
    TimePeriod singleYearPeriod = aTimePeriod().withYears(FIRST_YEAR, FIRST_YEAR).build();

    List<TimeCalendar> years = singleYearPeriod.getYears();

    assertThat(years).hasSize(1);
    assertThat(years.get(0).getYear()).isEqualTo(FIRST_YEAR);
  }

  @Test
  public void givenMultipleYearsPeriod_whenGettingYears_thenGetYears() {
    TimePeriod multipleYearsPeriod = aTimePeriod().withYears(FIRST_YEAR, SECOND_YEAR).build();

    List<TimeCalendar> years = multipleYearsPeriod.getYears();

    assertThat(years).hasSize(2);
    assertThat(years.get(0).getYear()).isEqualTo(FIRST_YEAR);
    assertThat(years.get(1).getYear()).isEqualTo(SECOND_YEAR);
  }

  @Test
  public void givenSingleMonthPeriod_whenGettingMonths_thenGetMonths() {
    TimePeriod singleMonthPeriod =
        aTimePeriod()
            .withYears(FIRST_YEAR, FIRST_YEAR)
            .withMonths(FIRST_MONTH, FIRST_MONTH)
            .build();

    List<TimeCalendar> months = singleMonthPeriod.getMonths();

    assertThat(months).hasSize(1);
    assertThat(months.get(0).getYear()).isEqualTo(FIRST_YEAR);
    assertThat(months.get(0).getMonth()).isEqualTo(toJavaCalendarMonth(FIRST_MONTH));
  }

  @Test
  public void givenMultipleMonthsPeriod_whenGettingMonths_thenGetMonths() {
    TimePeriod multipleMonthsPeriod =
        aTimePeriod()
            .withYears(FIRST_YEAR, FIRST_YEAR)
            .withMonths(FIRST_MONTH, SECOND_MONTH)
            .build();

    List<TimeCalendar> months = multipleMonthsPeriod.getMonths();

    assertThat(months).hasSize(2);
    assertThat(months.get(0).getYear()).isEqualTo(FIRST_YEAR);
    assertThat(months.get(0).getMonth()).isEqualTo(toJavaCalendarMonth(FIRST_MONTH));
    assertThat(months.get(1).getYear()).isEqualTo(FIRST_YEAR);
    assertThat(months.get(1).getMonth()).isEqualTo(toJavaCalendarMonth(SECOND_MONTH));
  }

  @Test
  public void givenMultipleYearsAndMonthsMonthsPeriod_whenGettingMonths_thenGetMonths() {
    TimePeriod multipleMonthsPeriod =
        aTimePeriod()
            .withYears(FIRST_YEAR, SECOND_YEAR)
            .withMonths(FIRST_MONTH, SECOND_MONTH)
            .build();

    List<TimeCalendar> months = multipleMonthsPeriod.getMonths();

    assertThat(months.get(0).getYear()).isEqualTo(FIRST_YEAR);
    assertThat(months.get(0).getMonth()).isEqualTo(toJavaCalendarMonth(FIRST_MONTH));
    assertThat(months.get(months.size() - 1).getYear()).isEqualTo(SECOND_YEAR);
    assertThat(months.get(months.size() - 1).getMonth())
        .isEqualTo(toJavaCalendarMonth(SECOND_MONTH));
  }

  @Test
  public void givenStartDateTime_whenCheckingIfContains_thenReturnTrue() {
    CustomDateTime dateTime = period.getStart();

    boolean result = period.contains(dateTime);

    assertThat(result).isTrue();
  }

  @Test
  public void givenEndDateTime_whenCheckingIfContains_thenReturnTrue() {
    CustomDateTime dateTime = period.getEnd();

    boolean result = period.contains(dateTime);

    assertThat(result).isTrue();
  }

  @Test
  public void givenContainedDateTime_whenCheckingIfContains_thenReturnTrue() {
    CustomDateTime dateTime = period.getStart().plusDays(1);

    boolean result = period.contains(dateTime);

    assertThat(result).isTrue();
  }

  @Test
  public void givenDateTimeBeforePeriod_whenCheckingIfContains_thenReturnFalse() {
    CustomDateTime dateTime = period.getStart().minusDays(1);

    boolean result = period.contains(dateTime);

    assertThat(result).isFalse();
  }

  @Test
  public void givenDateTimeAfterPeriod_whenCheckingIfContains_thenReturnFalse() {
    CustomDateTime dateTime = period.getEnd().plusDays(1);

    boolean result = period.contains(dateTime);

    assertThat(result).isFalse();
  }
}
