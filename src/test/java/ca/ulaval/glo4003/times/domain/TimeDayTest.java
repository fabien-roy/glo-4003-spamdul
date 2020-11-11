package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CalendarHelper.dateTimeAtMaximumTime;
import static ca.ulaval.glo4003.times.helpers.CalendarHelper.dateTimeAtMinimumTime;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.Before;
import org.junit.Test;

public class TimeDayTest {

  private final CustomDateTime dateTime = aDateTime().build();
  private final CustomDateTime otherDateTime = aDateTime().build();

  private TimeDay day;
  private TimeDay otherDay;

  @Before
  public void setUp() {
    day = new TimeDay(dateTime);
    otherDay = new TimeDay(otherDateTime);
  }

  @Test
  public void whenGettingPeriodStart_thenGetDayStart() {
    CustomDateTime expectedDayStart = aDateTime().withDateTime(getDayStart()).build();

    CustomDateTime dayStart = day.toPeriod().getStart();

    assertThat(dayStart).isEqualTo(expectedDayStart);
  }

  @Test
  public void whenGettingPeriodEnd_thenGetDayEnd() {
    CustomDateTime expectedDayEnd = aDateTime().withDateTime(getDayEnd()).build();

    CustomDateTime dayEnd = day.toPeriod().getEnd();

    assertThat(dayEnd).isEqualTo(expectedDayEnd);
  }

  @Test
  public void whenConvertingToString_thenReturnsDayAsString() {
    String expectedString = "02-02-2010";
    LocalDateTime localDateTime = LocalDateTime.of(2010, 2, 2, 0, 0, 0);
    CustomDateTime dateTime = new CustomDateTime(localDateTime);
    TimeDay day = new TimeDay(dateTime);

    String actualString = day.toString();

    assertThat(actualString).isEqualTo(expectedString);
  }

  // TODO : This doesn't test much, the logic is just pasted here. Fixed values would work better.
  @Test
  public void whenComparingDays_thenReturnDifferenceInDays() {
    LocalDateTime dateTimeAtMinimumTime = dateTimeAtMinimumTime(dateTime.toLocalDateTime());
    LocalDateTime otherDateTimeAtMinimumTime =
        dateTimeAtMinimumTime(otherDateTime.toLocalDateTime());
    int differenceInDays =
        (int) dateTimeAtMinimumTime.until(otherDateTimeAtMinimumTime, ChronoUnit.DAYS);
    int comparisonInDays =
        dateTimeAtMinimumTime.isBefore(otherDateTimeAtMinimumTime)
            ? differenceInDays
            : -differenceInDays;

    int comparison = day.compareTo(otherDay);

    assertThat(comparison).isEqualTo(comparisonInDays);
  }

  private LocalDateTime getDayStart() {
    int year = dateTime.toLocalDateTime().getYear();
    int month = dateTime.toLocalDateTime().getMonthValue();
    int dayOfMonth = dateTime.toLocalDateTime().getDayOfMonth();
    return dateTimeAtMinimumTime(year, month, dayOfMonth);
  }

  private LocalDateTime getDayEnd() {
    int year = dateTime.toLocalDateTime().getYear();
    int month = dateTime.toLocalDateTime().getMonthValue();
    int dayOfMonth = dateTime.toLocalDateTime().getDayOfMonth();
    return dateTimeAtMaximumTime(year, month, dayOfMonth);
  }
}
