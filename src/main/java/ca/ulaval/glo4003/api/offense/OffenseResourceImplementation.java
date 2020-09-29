package ca.ulaval.glo4003.api.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.offense.Offense;
import ca.ulaval.glo4003.domain.offense.OffenseService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OffenseResourceImplementation implements OffenseResource {
  private final OffenseService offenseService;

  public OffenseResourceImplementation(OffenseService offenseService) {
    this.offenseService = offenseService;
  }

  @Override
  public Response getAllOffenses() {
    Offense[] offenses = offenseService.getAllOffenses();
    return Response.status(Response.Status.OK)
        .entity(offenses)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Offense isOffenseNeeded(OffenseValidationDto OffenseValidationDto) {
    return offenseService.isOffenseNeeded(OffenseValidationDto);
  }
}
