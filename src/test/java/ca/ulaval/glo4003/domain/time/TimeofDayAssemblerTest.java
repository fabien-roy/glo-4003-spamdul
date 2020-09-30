package ca.ulaval.glo4003.domain.time;

import static ca.ulaval.glo4003.domain.time.helpers.TimeOfDayMother.createTimeOfDay;

import ca.ulaval.glo4003.domain.time.exception.InvalidTimeOfDayException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimeofDayAssemblerTest {
  private static final TimeOfDay TIME = createTimeOfDay();

  private TimeOfDayAssembler timeOfDayAssembler;

  @Before
  public void setUp() {
    timeOfDayAssembler = new TimeOfDayAssembler();
  }

  @Test
  public void whenAssembling_thenReturnTimeOfDay() {
    TimeOfDay timeOfDay = timeOfDayAssembler.assemble(TIME.toString());

    Truth.assertThat(timeOfDay).isEqualTo(TIME);
  }

  @Test(expected = InvalidTimeOfDayException.class)
  public void givenInvalidTime_whenAssembling_thenThrowInvalidTimeOfDayException() {
    timeOfDayAssembler.assemble("invalidTime");
  }

  @Test(expected = InvalidTimeOfDayException.class)
  public void givenNullTime_whenAssembling_thenThrowInvalidTimeOfDayException() {
    timeOfDayAssembler.assemble(null);
  }
}
