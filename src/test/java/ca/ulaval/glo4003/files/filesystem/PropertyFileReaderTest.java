package ca.ulaval.glo4003.files.filesystem;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.files.domain.exceptions.InvalidFileException;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;

public class PropertyFileReaderTest {

  private PropertyFileReader propertyFileReader;

  private static final String VALID_PROPERTIES_PATH = "data/test.properties";
  private static final String INVALID_PROPERTIES_PATH = "data/invalid.properties";

  @Before
  public void setUp() {
    propertyFileReader = new PropertyFileReader();
  }

  @Test
  public void whenReadingFile_thenConvertItToProperties() {
    Properties properties = propertyFileReader.readFile(VALID_PROPERTIES_PATH);

    assertThat(properties).isNotNull();
    assertThat(properties.getProperty("TEST")).isNotNull();
  }

  @Test(expected = InvalidFileException.class)
  public void givenInvalidFilePath_whenReadingFile_thenThrowInvalidFileException() {
    propertyFileReader.readFile(INVALID_PROPERTIES_PATH);
  }
}
