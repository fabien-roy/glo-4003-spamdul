package ca.ulaval.glo4003.api.contact;

import ca.ulaval.glo4003.api.contact.dto.PostUserDto;
import ca.ulaval.glo4003.api.contact.dto.UserDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  PostUserDto addUser(UserDto userDto);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  UserDto getUser(@PathParam("id") String id);
}
