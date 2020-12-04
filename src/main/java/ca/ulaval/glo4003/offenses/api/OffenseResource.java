package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import ca.ulaval.glo4003.offenses.services.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.services.dto.OffenseValidationDto;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/offenses")
public class OffenseResource {
  private final OffenseTypeService offenseTypeService;

  public OffenseResource(OffenseTypeService offenseTypeService) {
    this.offenseTypeService = offenseTypeService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("types")
  public Response getAllOffenses() {
    List<OffenseTypeDto> offenses = offenseTypeService.getAllOffenseTypes();
    GenericEntity<List<OffenseTypeDto>> entities =
        new GenericEntity<List<OffenseTypeDto>>(offenses) {};
    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("validate")
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
