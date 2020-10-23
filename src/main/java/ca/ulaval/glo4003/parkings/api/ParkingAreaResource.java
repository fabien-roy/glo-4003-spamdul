package ca.ulaval.glo4003.parkings.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/parkingAreas")
public interface ParkingAreaResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getParkingAreas();
}
