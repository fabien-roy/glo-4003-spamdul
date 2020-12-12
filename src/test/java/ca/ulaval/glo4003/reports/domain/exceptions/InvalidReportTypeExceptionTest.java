package ca.ulaval.glo4003.reports.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidReportTypeExceptionTest {
  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidReportTypeException();
  }

  @Test
  public void whenGettingDescription_thenEnumerateAccessPeriods() {
    String expectedDescription = "Report type should be one of dayOfMonth, monthly or summary";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
