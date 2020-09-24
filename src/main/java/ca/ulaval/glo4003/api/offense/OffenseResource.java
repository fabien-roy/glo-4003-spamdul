package ca.ulaval.glo4003.api.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/offense")
public interface OffenseResource {
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  OffenseDto addOffense(OffenseDto offenseDto);
}
