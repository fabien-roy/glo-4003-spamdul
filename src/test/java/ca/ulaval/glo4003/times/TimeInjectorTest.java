package ca.ulaval.glo4003.times;

import static com.google.common.truth.Truth.assertThat;

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
  public void whenCreatingCustomDateAssembler_thenReturnIt() {
    CustomDateConverter customDateConverter = timeInjector.createCustomDateAssembler();

    assertThat(customDateConverter).isNotNull();
  }

  @Test
  public void whenCreatingCustomDateTimeAssembler_thenReturnIt() {
    CustomDateTimeConverter customDateTimeConverter = timeInjector.createCustomDateTimeAssembler();

    assertThat(customDateTimeConverter).isNotNull();
  }

  @Test
  public void whenCreatingTimeOfDayAssembler_thenReturnIt() {
    TimeOfDayConverter timeOfDayConverter = timeInjector.createTimeOfDayAssembler();

    assertThat(timeOfDayConverter).isNotNull();
  }
}
