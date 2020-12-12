package ca.ulaval.glo4003.files.domain.exceptions;

import static ca.ulaval.glo4003.files.helpers.FileMother.createFilePath;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class InvalidFileExceptionTest {

  private ApplicationException exception;

  private final String filePath = createFilePath();

  @Before
  public void setUp() {
    exception = new InvalidFileException(filePath);
  }

  @Test
  public void whenGettingDescription_thenWriteFilePath() {
    String expectedDescription = String.format("File %s is invalid", filePath);

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
