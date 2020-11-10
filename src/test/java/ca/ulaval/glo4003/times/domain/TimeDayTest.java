package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

// TODO : Fixed values would test this better (ex : tests for toString())
public class TimeDayTest {

  private final CustomDateTime dateTime = aDateTime().build();

  private TimeDay day;

  @Before
  public void setUp() {
    day = new TimeDay(dateTime);
  }

  @Test
  public void whenConstructing_thenSetPeriodStartToDayStart() {
    CustomDateTime dayStart = aDateTime().withDateTime(getDayStart()).build();

    assertThat(day.toPeriod().getStart()).isEqualTo(dayStart);
  }

  private LocalDateTime getDayStart() {
    int year = dateTime.toLocalDateTime().getYear();
    int month = dateTime.toLocalDateTime().getMonthValue();
    int dayOfMonth = dateTime.toLocalDateTime().getDayOfMonth();
    return LocalDateTime.of(year, month, dayOfMonth, 0, 0);
  }
}
