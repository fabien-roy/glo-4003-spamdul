package ca.ulaval.glo4003.domain.parking;

public interface ParkingStickerRepository {
  void save(ParkingSticker parkingSticker);

  ParkingSticker findByCode(ParkingStickerCode code);
}
