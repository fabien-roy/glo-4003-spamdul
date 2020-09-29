package ca.ulaval.glo4003.api.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
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
    OffenseDto[] offenses = offenseService.getAllOffenses().toArray(new OffenseDto[0]);
    return Response.status(Response.Status.OK)
        .entity(offenses)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response isOffenseNeeded(OffenseValidationDto offenseValidationDto) {
    OffenseDto offense = offenseService.isOffenseNeeded(offenseValidationDto);
    return Response.status(Response.Status.OK)
        .entity(offense)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
