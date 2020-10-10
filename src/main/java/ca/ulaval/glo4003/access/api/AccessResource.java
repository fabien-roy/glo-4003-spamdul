package ca.ulaval.glo4003.access.api;

import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{accountId}/accessPasses")
public interface AccessResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  Response addAccessPass(AccessPassDto accessPassDto, @PathParam("accountId") String accountId);
}
