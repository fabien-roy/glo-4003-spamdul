package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;

public interface ParkingStickerRepository {
  void save(ParkingSticker parkingSticker);

  ParkingSticker findByCode(ParkingStickerCode code) throws NotFoundParkingStickerException;
}
