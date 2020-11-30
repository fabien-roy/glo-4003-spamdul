package ca.ulaval.glo4003.times.services.assemblers;

import static ca.ulaval.glo4003.times.helpers.DateTimeDtoBuilder.aDateTimeDto;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.exceptions.InvalidDateTimeException;
import ca.ulaval.glo4003.times.services.dto.DateTimeDto;
import org.junit.Before;
import org.junit.Test;

public class CustomDateTimeAssemblerTest {

  private CustomDateTimeAssembler customDateTimeAssembler;

  @Before
  public void setUp() {
    customDateTimeAssembler = new CustomDateTimeAssembler();
  }

  @Test
  public void whenAssembling_thenReturnCustomDate() {
    String validDateTime = "01-01-2020 14:30:30";
    DateTimeDto dateTimeDto = aDateTimeDto().withDateTime(validDateTime).build();

    CustomDateTime customDateTime = customDateTimeAssembler.assemble(dateTimeDto);

    assertThat(customDateTime).isNotNull();
  }

  @Test(expected = InvalidDateTimeException.class)
  public void givenNullDateTime_whenAssembling_thenThrowInvalidDateException() {
    DateTimeDto dateTimeDto = aDateTimeDto().withDateTime(null).build();

    customDateTimeAssembler.assemble(dateTimeDto);
  }

  @Test(expected = InvalidDateTimeException.class)
  public void givenInvalidDateTime_whenAssembling_thenThrowInvalidDateException() {
    DateTimeDto dateTimeDto = aDateTimeDto().withDateTime("invalidDateTime").build();

    customDateTimeAssembler.assemble(dateTimeDto);
  }
}
