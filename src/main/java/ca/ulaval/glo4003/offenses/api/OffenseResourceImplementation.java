package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OffenseResourceImplementation implements OffenseResource {
  private final OffenseTypeService offenseTypeService;

  public OffenseResourceImplementation(OffenseTypeService offenseTypeService) {
    this.offenseTypeService = offenseTypeService;
  }

  @Override
  public Response validateOffense(OffenseValidationDto offenseValidationDto) {
    List<OffenseTypeDto> offenses = offenseTypeService.validateOffense(offenseValidationDto);
    GenericEntity<List<OffenseTypeDto>> entities =
        new GenericEntity<List<OffenseTypeDto>>(offenses) {};
    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
