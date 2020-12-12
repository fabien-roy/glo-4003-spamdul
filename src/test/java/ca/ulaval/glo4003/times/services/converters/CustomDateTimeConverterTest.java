package ca.ulaval.glo4003.times.services.converters;

import static ca.ulaval.glo4003.times.helpers.DateTimeDtoBuilder.aDateTimeDto;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidDateTimeException;
import ca.ulaval.glo4003.times.services.dto.DateTimeDto;
import org.junit.Before;
import org.junit.Test;

public class CustomDateTimeConverterTest {

  private CustomDateTimeConverter customDateTimeConverter;

  @Before
  public void setUp() {
    customDateTimeConverter = new CustomDateTimeConverter();
  }

  @Test
  public void whenConverting_thenReturnCustomDate() {
    String validDateTime = "01-01-2020 14:30:30";
    DateTimeDto dateTimeDto = aDateTimeDto().withDateTime(validDateTime).build();

    CustomDateTime customDateTime = customDateTimeConverter.convert(dateTimeDto);

    assertThat(customDateTime).isNotNull();
  }

  @Test(expected = InvalidDateTimeException.class)
  public void givenNullDateTime_whenConverting_thenThrowInvalidDateException() {
    DateTimeDto dateTimeDto = aDateTimeDto().withDateTime(null).build();

    customDateTimeConverter.convert(dateTimeDto);
  }

  @Test(expected = InvalidDateTimeException.class)
  public void givenInvalidDateTime_whenConverting_thenThrowInvalidDateException() {
    DateTimeDto dateTimeDto = aDateTimeDto().withDateTime("invalidDateTime").build();

    customDateTimeConverter.convert(dateTimeDto);
  }
}
