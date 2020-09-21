package ca.ulaval.glo4003.api.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.parking.ParkingService;

public class ParkingResourceImpl implements ParkingResource {
  private final ParkingService parkingService;

  public ParkingResourceImpl(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public void addParkingSticker(ParkingStickerDto parkingStickerDto) {
    parkingService.addParkingSticker(parkingStickerDto);
  }
}
