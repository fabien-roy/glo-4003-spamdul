package ca.ulaval.glo4003.api.user;

import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import ca.ulaval.glo4003.api.user.dto.UserDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  AccountIdDto addUser(UserDto userDto);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  UserDto getUser(@PathParam("id") String id);
}
