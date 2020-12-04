package ca.ulaval.glo4003.times;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.services.SemesterService;
import ca.ulaval.glo4003.times.services.converters.CustomDateConverter;
import ca.ulaval.glo4003.times.services.converters.CustomDateTimeConverter;
import ca.ulaval.glo4003.times.services.converters.TimeOfDayConverter;
import org.junit.Before;
import org.junit.Test;

public class TimeInjectorTest {

  private TimeInjector timeInjector;

  @Before
  public void setUp() {
    timeInjector = new TimeInjector();
  }

  @Test
  public void whenCreatingCustomDateConverter_thenReturnIt() {
    CustomDateConverter customDateConverter = timeInjector.createCustomDateConverter();

    assertThat(customDateConverter).isNotNull();
  }

  @Test
  public void whenCreatingCustomDateTimeConverter_thenReturnIt() {
    CustomDateTimeConverter customDateTimeConverter = timeInjector.createCustomDateTimeConverter();

    assertThat(customDateTimeConverter).isNotNull();
  }

  @Test
  public void whenCreatingTimeOfDayConverter_thenReturnIt() {
    TimeOfDayConverter timeOfDayConverter = timeInjector.createTimeOfDayConverter();

    assertThat(timeOfDayConverter).isNotNull();
  }

  @Test
  public void whenCreatingSemesterService_thenReturnIt() {
    SemesterService semesterService = timeInjector.createSemesterService();

    assertThat(semesterService).isNotNull();
  }
}
