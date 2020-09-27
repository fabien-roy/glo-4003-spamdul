package ca.ulaval.glo4003.api.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/parkings")
public interface ParkingResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response addParkingSticker(ParkingStickerDto parkingStickerDto);
}
