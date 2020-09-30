package ca.ulaval.glo4003.injection.time;

import ca.ulaval.glo4003.domain.time.CustomDateAssembler;
import ca.ulaval.glo4003.domain.time.TimeOfDayAssembler;

public class TimeResourceConfig {

  public CustomDateAssembler createCustomDateAssembler() {
    return new CustomDateAssembler();
  }

  public TimeOfDayAssembler createTimeOfDayAssembler() {
    return new TimeOfDayAssembler();
  }
}
