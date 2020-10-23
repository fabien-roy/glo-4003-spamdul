package ca.ulaval.glo4003.parkings.api;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaCodeDto;
import ca.ulaval.glo4003.parkings.services.ParkingAreaCodeService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ParkingAreaCodeResourceImplementation implements ParkingAreaCodeResource {
  private final ParkingAreaCodeService parkingAreaCodeService;

  public ParkingAreaCodeResourceImplementation(ParkingAreaCodeService parkingAreaCodeService) {
    this.parkingAreaCodeService = parkingAreaCodeService;
  }

  @Override
  public Response getParkingAreas() {
    List<ParkingAreaCodeDto> parkingAreasDto = parkingAreaCodeService.getParkingAreas();
    GenericEntity<List<ParkingAreaCodeDto>> entities =
        new GenericEntity<List<ParkingAreaCodeDto>>(parkingAreasDto) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
