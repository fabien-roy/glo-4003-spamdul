package ca.ulaval.glo4003.carboncredits.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidMonthlyPaymentStatusExceptionTest {

  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidMonthlyPaymentStatusException();
  }

  @Test
  public void whenGettingDescription_thenEnumerateMonthlyPaymentStatus() {
    String expectedDescription = "Monthly payment status should be one of enable or disable";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
