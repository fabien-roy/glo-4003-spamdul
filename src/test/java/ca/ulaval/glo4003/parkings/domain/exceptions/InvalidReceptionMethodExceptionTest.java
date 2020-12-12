package ca.ulaval.glo4003.parkings.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidReceptionMethodExceptionTest {

  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidReceptionMethodException();
  }

  @Test
  public void whenGettingDescription_thenEnumerateAccessPeriods() {
    String expectedDescription = "Reception method should be one of postal, email or ssp";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
