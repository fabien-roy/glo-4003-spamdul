package ca.ulaval.glo4003.injection.time;

import ca.ulaval.glo4003.domain.time.CustomDateAssembler;

public class TimeResourceConfig {

  public CustomDateAssembler createCustomDateAssembler() {
    return new CustomDateAssembler();
  }
}
