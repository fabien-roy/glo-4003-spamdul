package ca.ulaval.glo4003.parkings.api;

import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/parkings")
public interface ParkingResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response addParkingSticker(ParkingStickerDto parkingStickerDto);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{code}/validate")
  Response validateParkingSticker(@PathParam("code") String parkingStickerCode);
}
