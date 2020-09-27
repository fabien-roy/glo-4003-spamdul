package ca.ulaval.glo4003.api.parking;

import ca.ulaval.glo4003.api.parking.dto.AccessStatusDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
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
  public Response validateParkingStickerCode(ParkingStickerCodeDto parkingStickerCodeDto) {
    AccessStatusDto accessStatusDto =
        parkingService.validateParkingStickerCode(parkingStickerCodeDto);
    return Response.status(Response.Status.ACCEPTED)
        .entity(accessStatusDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
