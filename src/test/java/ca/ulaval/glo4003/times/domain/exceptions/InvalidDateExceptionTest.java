package ca.ulaval.glo4003.times.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidDateExceptionTest {
  private static final String FORMAT = "format";

  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidDateException(FORMAT);
  }

  @Test
  public void whenGettingDescription_thenWriteFormat() {
    String expectedDescription = String.format("Date must be of format : %s", FORMAT);

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
