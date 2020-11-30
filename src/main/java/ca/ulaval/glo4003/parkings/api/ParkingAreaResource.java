package ca.ulaval.glo4003.parkings.api;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/parkingAreas")
public class ParkingAreaResource {
  private final ParkingAreaService parkingAreaService;

  public ParkingAreaResource(ParkingAreaService parkingAreaService) {
    this.parkingAreaService = parkingAreaService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getParkingAreas() {
    List<ParkingAreaDto> parkingAreasDto = parkingAreaService.getParkingAreas();
    GenericEntity<List<ParkingAreaDto>> entities =
        new GenericEntity<List<ParkingAreaDto>>(parkingAreasDto) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
