package ca.ulaval.glo4003.users.api;

import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public interface UserResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response addUser(UserDto userDto);

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/parkingStickers")
  Response addParkingSticker(
      @PathParam("accountId") String accountId, ParkingStickerDto parkingStickerDto);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}")
  Response getUser(@PathParam("accountId") String accountId);
}
