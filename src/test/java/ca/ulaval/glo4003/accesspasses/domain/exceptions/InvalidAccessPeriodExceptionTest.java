package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidAccessPeriodExceptionTest {

  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidAccessPeriodException();
  }

  @Test
  public void whenGettingDescription_thenEnumerateAccessPeriods() {
    String expectedDescription =
        "Access period should be one of 1h, 1d, 1d/week/semester, 1 semester, 2 semesters or 3 semesters";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
