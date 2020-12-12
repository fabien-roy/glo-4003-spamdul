package ca.ulaval.glo4003.times.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidTimeOfDayExceptionTest {
  private static final String FORMAT = "format";

  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidTimeOfDayException(FORMAT);
  }

  @Test
  public void whenGettingDescription_thenWriteFormat() {
    String expectedDescription = String.format("Time of day must be of format : %s", FORMAT);

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
