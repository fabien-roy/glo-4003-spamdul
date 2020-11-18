package ca.ulaval.glo4003.gates.assemblers;

import ca.ulaval.glo4003.gates.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class DayOfWeekAssembler {
  public DayOfWeek assemble(DayOfWeekDto dayOfWeekDto) {
    return DayOfWeek.get(dayOfWeekDto.dayOfWeek);
  }
}
