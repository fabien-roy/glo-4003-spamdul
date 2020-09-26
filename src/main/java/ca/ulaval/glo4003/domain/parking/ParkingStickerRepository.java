package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingStickerCodeException;

public interface ParkingStickerRepository {
  void save(ParkingSticker parkingSticker);

  ParkingSticker findByCode(ParkingStickerCode code) throws NotFoundParkingStickerCodeException;
}
