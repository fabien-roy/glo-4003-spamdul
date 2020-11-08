package ca.ulaval.glo4003.times.domain;

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
  public void getYears_withMultipleYearsPeriod_shouldGetYears() {
    TimePeriod multipleYearsPeriod = aTimePeriod().withYears(FIRST_YEAR, SECOND_YEAR).build();

    List<TimeCalendar> years = multipleYearsPeriod.getYears();

    assertThat(years).hasSize(2);
    assertThat(years.get(0).getYear()).isEqualTo(FIRST_YEAR);
    assertThat(years.get(1).getYear()).isEqualTo(SECOND_YEAR);
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
