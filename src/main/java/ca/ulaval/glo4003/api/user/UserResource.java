package ca.ulaval.glo4003.api.user;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public interface UserResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response addUser(UserDto userDto);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}")
  Response getUser(@PathParam("accountId") String accountId);
}
