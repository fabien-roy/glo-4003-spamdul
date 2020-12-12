package ca.ulaval.glo4003.times.services.converters;

import static ca.ulaval.glo4003.times.helpers.TimeOfDayMother.createTimeOfDay;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.TimeOfDay;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidTimeOfDayException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimeOfDayConverterTest {
  private static final TimeOfDay TIME = createTimeOfDay();

  private TimeOfDayConverter timeOfDayConverter;

  @Before
  public void setUp() {
    timeOfDayConverter = new TimeOfDayConverter();
  }

  @Test
  public void whenConverting_thenReturnTimeOfDay() {
    TimeOfDay timeOfDay = timeOfDayConverter.convert(TIME.toString());

    assertThat(timeOfDay).isEqualTo(TIME);
  }

  @Test(expected = InvalidTimeOfDayException.class)
  public void givenInvalidTime_whenConverting_thenThrowInvalidTimeOfDayException() {
    timeOfDayConverter.convert("invalidTime");
  }

  @Test(expected = InvalidTimeOfDayException.class)
  public void givenNullTime_whenConverting_thenThrowInvalidTimeOfDayException() {
    timeOfDayConverter.convert(null);
  }
}
