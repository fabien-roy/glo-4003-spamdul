package ca.ulaval.glo4003.users.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidSexExceptionTest {
  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidSexException();
  }

  @Test
  public void whenGettingDescription_thenEnumerateSex() {
    String expectedDescription = "Sex should be one of m, f or x";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
