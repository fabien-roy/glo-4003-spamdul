package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OffenseResourceImplementation implements OffenseResource {
  private final OffenseTypeService offenseTypeService;

  public OffenseResourceImplementation(OffenseTypeService offenseTypeService) {
    this.offenseTypeService = offenseTypeService;
  }

  @Override
  public Response validateOffense(OffenseValidationDto offenseValidationDto) {
    OffenseTypeDto offense = offenseTypeService.validateOffense(offenseValidationDto);
    return Response.status(Response.Status.OK)
        .entity(offense)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
