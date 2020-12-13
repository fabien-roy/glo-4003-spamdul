package ca.ulaval.glo4003.times.domain.exceptions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidSemesterSymbolExceptionTest {
  private ApplicationException exception;

  @Before
  public void setUp() {
    exception = new InvalidSemesterSymbolException();
  }

  @Test
  public void whenGettingDescription_thenWriteSymbols() {
    String expectedDescription = "Semester symbol must be one of H, E or A";

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
