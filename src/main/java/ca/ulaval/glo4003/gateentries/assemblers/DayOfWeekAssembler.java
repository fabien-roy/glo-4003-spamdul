package ca.ulaval.glo4003.gateentries.assemblers;

import ca.ulaval.glo4003.gateentries.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.gateentries.exceptions.InvalidDayOfWeekException;
import ca.ulaval.glo4003.times.domain.Days;
import ca.ulaval.glo4003.times.exceptions.InvalidDayException;

public class DayOfWeekAssembler {
  public Days assemble(DayOfWeekDto dayOfWeekDto) {
    try {
      return Days.get(dayOfWeekDto.dayOfWeek);
    } catch (InvalidDayException exception) {
      throw new InvalidDayOfWeekException();
    }
  }
}
