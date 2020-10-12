package ca.ulaval.glo4003.files.filesystem;

import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import com.google.common.truth.Truth;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CsvFileReaderTest {

  private CsvFileReader csvFileReader;

  private static final String VALID_CSV_PATH = "data/frais-zone.csv";
  private static final String INVALID_CSV_PATH = "data/invalid.csv";

  @Before
  public void setUp() {
    csvFileReader = new CsvFileReader();
  }

  @Test
  public void whenReadingFile_thenConvertItToAStringMatrix() {
    List<List<String>> stringMatrix = csvFileReader.readFile(VALID_CSV_PATH);

    Truth.assertThat(stringMatrix).isNotEmpty();
    Truth.assertThat(stringMatrix.get(0)).isNotEmpty();
  }

  @Test(expected = InvalidFileException.class)
  public void givenInvalidFilePath_whenReadingFile_thenThrowInvalidFileException() {
    csvFileReader.readFile(INVALID_CSV_PATH);
  }
}
