package ca.ulaval.glo4003.files;

import ca.ulaval.glo4003.files.filesystem.JsonFileReader;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class FileInjectorTest {

  private FileInjector fileInjector;

  @Before
  public void setUp() {
    fileInjector = new FileInjector();
  }

  @Test
  public void whenCreatingJsonReader_thenReturnIt() {
    JsonFileReader jsonReader = fileInjector.createJsonFileReader();

    Truth.assertThat(jsonReader).isNotNull();
  }
}
