package ca.ulaval.glo4003.parkings.api;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ParkingAreaResourceImplementation implements ParkingAreaCodeResource {
  private final ParkingAreaService parkingAreaService;

  public ParkingAreaResourceImplementation(ParkingAreaService parkingAreaService) {
    this.parkingAreaService = parkingAreaService;
  }

  @Override
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
