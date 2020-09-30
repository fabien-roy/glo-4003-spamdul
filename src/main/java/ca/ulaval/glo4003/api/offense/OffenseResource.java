package ca.ulaval.glo4003.api.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/offenses")
public interface OffenseResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getAllOffenses();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/validate")
  Response isOffenseNeeded(OffenseValidationDto OffenseValidationDto);
}
