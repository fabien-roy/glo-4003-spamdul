package ca.ulaval.glo4003.domain.parking;

import javax.inject.Inject;

public class ParkingStickerFactory {
  private final ParkingStickerCodeGenerator parkingStickerCodeGenerator;

  @Inject
  public ParkingStickerFactory(ParkingStickerCodeGenerator parkingStickerCodeGenerator) {
    this.parkingStickerCodeGenerator = parkingStickerCodeGenerator;
  }

  public ParkingSticker create(ParkingSticker parkingSticker) {
    ParkingStickerCode code = parkingStickerCodeGenerator.generate();
    parkingSticker.setCode(code);
    return parkingSticker;
  }
}
