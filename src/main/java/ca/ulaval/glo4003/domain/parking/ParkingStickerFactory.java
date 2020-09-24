package ca.ulaval.glo4003.domain.parking;

public class ParkingStickerFactory {
  private final ParkingStickerCodeGenerator parkingStickerCodeGenerator;

  public ParkingStickerFactory(ParkingStickerCodeGenerator parkingStickerCodeGenerator) {
    this.parkingStickerCodeGenerator = parkingStickerCodeGenerator;
  }

  public ParkingSticker create(ParkingSticker parkingSticker) {
    ParkingStickerCode code = parkingStickerCodeGenerator.generate();
    parkingSticker.setCode(code);
    return parkingSticker;
  }
}
