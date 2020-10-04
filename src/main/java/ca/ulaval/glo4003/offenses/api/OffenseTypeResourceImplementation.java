package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.services.OffenseService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OffenseTypeResourceImplementation implements OffenseTypeResource {
  private final OffenseService offenseService;

  public OffenseTypeResourceImplementation(OffenseService offenseService) {
    this.offenseService = offenseService;
  }

  @Override
  public Response getAllOffenses() {
    List<OffenseTypeDto> offenses = offenseService.getAllOffenses();
    GenericEntity<List<OffenseTypeDto>> entities =
        new GenericEntity<List<OffenseTypeDto>>(offenses) {};
    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
