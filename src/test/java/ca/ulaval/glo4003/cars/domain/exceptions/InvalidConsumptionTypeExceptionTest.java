package ca.ulaval.glo4003.cars.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidConsumptionTypeExceptionTest {

  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidConsumptionTypeException();
  }

  @Test
  public void whenGettingDescription_thenEnumerateConsumptionTypes() {
    String expectedDescription =
        "Car consumption should be one of greedy, economic, economical hybrid, super economical or 0 pollution";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
