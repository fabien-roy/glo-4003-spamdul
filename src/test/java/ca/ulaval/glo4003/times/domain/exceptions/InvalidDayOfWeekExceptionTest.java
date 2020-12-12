package ca.ulaval.glo4003.times.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidDayOfWeekExceptionTest {
  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidDayOfWeekException();
  }

  @Test
  public void whenGettingDescription_thenEnumerateDaysOfWeek() {
    String expectedDescription =
        "Day of week should be one of monday, tuesday, wednesday, thursday, friday, saturday or sunday";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
