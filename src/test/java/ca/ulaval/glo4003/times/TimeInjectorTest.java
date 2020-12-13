package ca.ulaval.glo4003.times;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.services.SemesterService;
import org.junit.Before;
import org.junit.Test;

public class TimeInjectorTest {

  private TimeInjector timeInjector;

  @Before
  public void setUp() {
    timeInjector = new TimeInjector();
  }

  @Test
  public void whenCreatingSemesterService_thenReturnIt() {
    SemesterService semesterService = timeInjector.createSemesterService();

    assertThat(semesterService).isNotNull();
  }
}
