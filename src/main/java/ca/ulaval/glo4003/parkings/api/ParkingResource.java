package ca.ulaval.glo4003.parkings.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/parkings")
public interface ParkingResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{code}/validate")
  Response validateParkingSticker(@PathParam("code") String parkingStickerCode);
}
