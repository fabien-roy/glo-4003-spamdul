package ca.ulaval.glo4003.api.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import ca.ulaval.glo4003.domain.offense.OffenseService;

public class OffenseResourceImplementation implements OffenseResource {
  private final OffenseService offenseService;

  public OffenseResourceImplementation(OffenseService offenseService) {
    this.offenseService = offenseService;
  }

  @Override
  public OffenseDto addOffense(OffenseDto offenseDto) {
    return offenseService.addOffense(offenseDto);
  }
}
