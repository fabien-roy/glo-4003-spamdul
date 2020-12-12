package ca.ulaval.glo4003.parkings.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidParkingPeriodExceptionTest {
  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidParkingPeriodException();
  }

  @Test
  public void whenGettingDescription_thenEnumerateAccessPeriods() {
    String expectedDescription =
        "Parking period should be one of 1d/week/semester, monthly, 1 semester, 2 semesters or 3 semesters";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
