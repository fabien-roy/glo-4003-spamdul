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

  @Test
  public void whenRemovingDays_thenReturnDateTimeMinusDays() {
    LocalDateTime dateTimeMinusDays = localDateTime.minusDays(amountOfDays);

    CustomDateTime actualDateTime = dateTime.minusDays(amountOfDays);

    assertThat(actualDateTime.toLocalDateTime()).isEqualTo(dateTimeMinusDays);
  }

  @Test
  public void givenBeforeDateTime_whenCheckingIfIsBefore_thenReturnFalse() {
    CustomDateTime dateTimeBefore = new CustomDateTime(localDateTime.minusDays(1));

    boolean result = dateTime.isBefore(dateTimeBefore);

    assertThat(result).isFalse();
  }

  @Test
  public void givenEqualDateTime_whenCheckingIfIsBefore_thenReturnFalse() {
    CustomDateTime dateTimeEqual = new CustomDateTime(localDateTime);

    boolean result = dateTime.isBefore(dateTimeEqual);

    assertThat(result).isFalse();
  }

  @Test
  public void givenAfterDateTime_whenCheckingIfIsBefore_thenReturnTrue() {
    CustomDateTime dateTimeAfter = new CustomDateTime(localDateTime.plusDays(1));

    boolean result = dateTime.isBefore(dateTimeAfter);

    assertThat(result).isTrue();
  }

  @Test
  public void givenBeforeDateTime_whenCheckingIfIsAfter_thenReturnTrue() {
    CustomDateTime dateTimeBefore = new CustomDateTime(localDateTime.minusDays(1));

    boolean result = dateTime.isAfter(dateTimeBefore);

    assertThat(result).isTrue();
  }

  @Test
  public void givenEqualDateTime_whenCheckingIfIsAfter_thenReturnFalse() {
    CustomDateTime dateTimeEqual = new CustomDateTime(localDateTime);

    boolean result = dateTime.isAfter(dateTimeEqual);

    assertThat(result).isFalse();
  }

  @Test
  public void givenAfterDateTime_whenCheckingIfIsAfter_thenReturnFalse() {
    CustomDateTime dateTimeAfter = new CustomDateTime(localDateTime.plusDays(1));

    boolean result = dateTime.isAfter(dateTimeAfter);

    assertThat(result).isFalse();
  }

  @Test
  public void whenGetDayOfWeek_thenReturnCorrectDay() {
    DayOfWeek expectedDay = DayOfWeek.get(dateTime.toLocalDateTime().getDayOfWeek().toString());
    DayOfWeek computedDay = dateTime.getDayOfWeek();

    assertThat(expectedDay.equals(computedDay));
  }
}
