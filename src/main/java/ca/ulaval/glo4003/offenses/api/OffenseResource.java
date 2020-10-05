package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/offenses")
public interface OffenseResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("types")
  Response getAllOffenses();

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("validate")
  Response validateOffense(OffenseValidationDto OffenseValidationDto);
}
