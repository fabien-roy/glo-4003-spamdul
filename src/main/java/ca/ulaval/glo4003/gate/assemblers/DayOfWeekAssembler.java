package ca.ulaval.glo4003.gate.assemblers;

import ca.ulaval.glo4003.gate.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class DayOfWeekAssembler {
  public DayOfWeek assemble(DayOfWeekDto dayOfWeekDto) {
    return DayOfWeek.get(dayOfWeekDto.dayOfWeek);
  }
}
