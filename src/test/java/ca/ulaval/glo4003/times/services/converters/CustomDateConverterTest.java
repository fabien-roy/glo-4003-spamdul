package ca.ulaval.glo4003.times.services.converters;

import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidDateException;
import org.junit.Before;
import org.junit.Test;

public class CustomDateConverterTest {
  private CustomDateConverter customDateConverter;

  private final CustomDate date = createPastDate();

  @Before
  public void setUp() {
    customDateConverter = new CustomDateConverter();
  }

  @Test
  public void whenConverting_thenReturnCustomDate() {
    CustomDate customDate = customDateConverter.convert(date.toString());

    assertThat(customDate).isEqualTo(date);
  }

  @Test(expected = InvalidDateException.class)
  public void givenInvalidDate_whenConverting_thenThrowInvalidDateException() {
    String invalidDate = "invalidDate";

    customDateConverter.convert(invalidDate);
  }
}
