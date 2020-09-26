package ca.ulaval.glo4003.api.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.parking.ParkingService;

public class ParkingResourceImplementation implements ParkingResource {
  private final ParkingService parkingService;

  public ParkingResourceImplementation(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public ParkingStickerCodeDto addParkingSticker(ParkingStickerDto parkingStickerDto) {
    return parkingService.addParkingSticker(parkingStickerDto);
  }

  @Override
  public String validateParkingStickerCode(ParkingStickerCodeDto parkingStickerCodeDto) {
    return parkingService.validateParkingStickerCode(parkingStickerCodeDto);
  }
}
