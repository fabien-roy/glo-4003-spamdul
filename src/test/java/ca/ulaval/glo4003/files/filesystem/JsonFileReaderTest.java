package ca.ulaval.glo4003.files.filesystem;

import ca.ulaval.glo4003.files.domain.exceptions.InvalidFileException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class JsonFileReaderTest {

  private JsonFileReader jsonFileReader;

  private static final String VALID_JSON_PATH = "data/infraction.json";
  private static final String INVALID_JSON_PATH = "data/invalid.json";

  @Before
  public void setUp() {
    jsonFileReader = new JsonFileReader();
  }

  @Test
  public void whenReadingFile_thenConvertItToAString() {
    String string = jsonFileReader.readFile(VALID_JSON_PATH);

    Truth.assertThat(string).isNotNull();
  }

  @Test(expected = InvalidFileException.class)
  public void givenInvalidFilePath_whenReadingFile_thenThrowInvalidFileException() {
    jsonFileReader.readFile(INVALID_JSON_PATH);
  }
}
