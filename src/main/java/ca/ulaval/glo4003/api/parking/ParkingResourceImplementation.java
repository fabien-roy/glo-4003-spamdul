package ca.ulaval.glo4003.api.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.parking.ParkingService;

import javax.inject.Inject;

public class ParkingResourceImplementation implements ParkingResource {
  private final ParkingService parkingService;

  @Inject
  public ParkingResourceImplementation(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public ParkingStickerCodeDto addParkingSticker(ParkingStickerDto parkingStickerDto) {
    return parkingService.addParkingSticker(parkingStickerDto);
  }
}
