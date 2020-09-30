package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.offenses.api.dto.OffenseDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.services.OffenseService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OffenseResourceImplementation implements OffenseResource {
  private final OffenseService offenseService;

  public OffenseResourceImplementation(OffenseService offenseService) {
    this.offenseService = offenseService;
  }

  @Override
  public Response getAllOffenses() {
    List<OffenseDto> offenses = offenseService.getAllOffenses();
    GenericEntity<List<OffenseDto>> entities = new GenericEntity<List<OffenseDto>>(offenses) {};
    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response validateOffense(OffenseValidationDto offenseValidationDto) {
    OffenseDto offense = offenseService.validateOffense(offenseValidationDto);
    return Response.status(Response.Status.OK)
        .entity(offense)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
