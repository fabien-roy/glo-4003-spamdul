package ca.ulaval.glo4003.times;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.services.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.times.services.assemblers.CustomDateTimeAssembler;
import ca.ulaval.glo4003.times.services.assemblers.TimeOfDayAssembler;
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
    CustomDateAssembler customDateAssembler = timeInjector.createCustomDateAssembler();

    assertThat(customDateAssembler).isNotNull();
  }

  @Test
  public void whenCreatingCustomDateTimeAssembler_thenReturnIt() {
    CustomDateTimeAssembler customDateTimeAssembler = timeInjector.createCustomDateTimeAssembler();

    assertThat(customDateTimeAssembler).isNotNull();
  }

  @Test
  public void whenCreatingTimeOfDayAssembler_thenReturnIt() {
    TimeOfDayAssembler timeOfDayAssembler = timeInjector.createTimeOfDayAssembler();

    assertThat(timeOfDayAssembler).isNotNull();
  }
}
