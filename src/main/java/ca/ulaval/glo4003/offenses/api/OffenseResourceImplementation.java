package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.services.OffenseService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OffenseResourceImplementation implements OffenseResource {
  private final OffenseService offenseService;

  public OffenseResourceImplementation(OffenseService offenseService) {
    this.offenseService = offenseService;
  }

  @Override
  public Response validateOffense(OffenseValidationDto offenseValidationDto) {
    OffenseTypeDto offense = offenseService.validateOffense(offenseValidationDto);
    return Response.status(Response.Status.OK)
        .entity(offense)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
