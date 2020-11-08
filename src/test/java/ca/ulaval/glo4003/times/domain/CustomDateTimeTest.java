package ca.ulaval.glo4003.times.domain;

import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createLocalDateTime;
import static ca.ulaval.glo4003.times.helpers.TimePeriodMother.createAmountOfDays;
import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class CustomDateTimeTest {

  private final LocalDateTime localDateTime = createLocalDateTime();
  private final int amountOfDays = createAmountOfDays();

  private CustomDateTime dateTime;

  @Before
  public void setUp() {
    dateTime = new CustomDateTime(localDateTime);
  }

  @Test
  public void whenAddingDays_thenReturnDateTimePlusDays() {
    LocalDateTime dateTimePlusDays = localDateTime.plusDays(amountOfDays);

    CustomDateTime actualDateTime = dateTime.plusDays(amountOfDays);

    assertThat(actualDateTime.toLocalDateTime()).isEqualTo(dateTimePlusDays);
  }
}
