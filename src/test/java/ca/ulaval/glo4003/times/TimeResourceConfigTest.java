package ca.ulaval.glo4003.times;

import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class TimeResourceConfigTest {

  private TimeResourceConfig timeResourceConfig;

  @Before
  public void setUp() {
    timeResourceConfig = new TimeResourceConfig();
  }

  @Test
  public void whenCreatingCustomDateAssembler_thenReturnIt() {
    CustomDateAssembler customDateAssembler = timeResourceConfig.createCustomDateAssembler();

    Truth.assertThat(customDateAssembler).isNotNull();
  }
}
