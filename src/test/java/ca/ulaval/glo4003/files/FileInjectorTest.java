package ca.ulaval.glo4003.files;

import ca.ulaval.glo4003.files.filesystem.JsonHelper;
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
  public void whenCreatingJsonHelper_thenReturnIt() {
    JsonHelper jsonHelper = fileInjector.createJsonHelper();

    Truth.assertThat(jsonHelper).isNotNull();
  }
}
