package ca.ulaval.glo4003.times;

import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import com.google.common.truth.Truth;
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

    Truth.assertThat(customDateAssembler).isNotNull();
  }
}
