package ca.ulaval.glo4003.times;

import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.times.assemblers.TimeOfDayAssembler;

public class TimeInjector {

  public CustomDateAssembler createCustomDateAssembler() {
    return new CustomDateAssembler();
  }

  public TimeOfDayAssembler createTimeOfDayAssembler() {
    return new TimeOfDayAssembler();
  }
}
