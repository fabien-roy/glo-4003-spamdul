package ca.ulaval.glo4003.gate.assemblers;

import static ca.ulaval.glo4003.gate.api.helpers.DayOfWeekDtoBuilder.aDayOfWeekDto;
import static ca.ulaval.glo4003.times.helpers.DayOfWeekMother.createDayOfWeek;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.gate.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.exceptions.InvalidDayOfWeekException;
import org.junit.Before;
import org.junit.Test;

public class DayOfWeekAssemblerTest {

  private DayOfWeekAssembler dayOfWeekAssembler;

  @Before
  public void setUp() {
    dayOfWeekAssembler = new DayOfWeekAssembler();
  }

  @Test
  public void givenValidDayOfWeek_whenAssembling_thenReturnDayOfWeek() {
    DayOfWeek dayOfWeek = createDayOfWeek();
    DayOfWeekDto dayOfWeekDto = aDayOfWeekDto().withDayOfWeek(dayOfWeek.toString()).build();

    DayOfWeek assembledDayOfWeek = dayOfWeekAssembler.assemble(dayOfWeekDto);

    assertThat(assembledDayOfWeek).isEqualTo(dayOfWeek);
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void givenInvalidDayOfWeek_whenAssembling_thenThrowInvalidDayOfWeekException() {
    String invalidDayOfWeek = "invalidDayOfWeek";
    DayOfWeekDto dayOfWeekDto = aDayOfWeekDto().withDayOfWeek(invalidDayOfWeek).build();

    dayOfWeekAssembler.assemble(dayOfWeekDto);
  }
}
