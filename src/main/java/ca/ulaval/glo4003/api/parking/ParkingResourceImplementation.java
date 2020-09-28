package ca.ulaval.glo4003.api.parking;

import ca.ulaval.glo4003.api.parking.dto.AccessStatusDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.parking.AccessStatus;
import ca.ulaval.glo4003.domain.parking.ParkingService;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ParkingResourceImplementation implements ParkingResource {
  private final ParkingService parkingService;

  @Inject
  public ParkingResourceImplementation(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Response addParkingSticker(ParkingStickerDto parkingStickerDto) {
    ParkingStickerCodeDto parkingStickerCodeDto =
        parkingService.addParkingSticker(parkingStickerDto);
    return Response.status(Response.Status.CREATED)
        .entity(parkingStickerCodeDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response validateParkingSticker(String parkingStickerCode) {
    AccessStatusDto accessStatusDto = parkingService.validateParkingStickerCode(parkingStickerCode);

    Response.Status status;
    if (AccessStatus.ACCESS_REFUSED.toString().equals(accessStatusDto.accessStatus)) {
      status = Response.Status.FORBIDDEN;
    } else {
      status = Response.Status.ACCEPTED;
    }

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }
}
