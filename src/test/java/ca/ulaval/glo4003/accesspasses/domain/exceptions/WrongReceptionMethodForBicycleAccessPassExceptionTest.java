package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

public class WrongReceptionMethodForBicycleAccessPassExceptionTest {

  private WrongReceptionMethodForBicycleAccessPassException exception;

  @Before
  public void setUp() {
    exception = new WrongReceptionMethodForBicycleAccessPassException();
  }

  @Test
  public void whenGettingDescription_thenReturnCorrectDescription() {
    String expectedDescription = "The reception method provided doesn't match any reception method";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
