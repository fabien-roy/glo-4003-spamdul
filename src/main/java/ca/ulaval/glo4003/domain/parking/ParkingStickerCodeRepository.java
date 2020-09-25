package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingStickerCodeException;

public interface ParkingStickerCodeRepository {
  ParkingStickerCode findById(ParkingStickerCode code) throws NotFoundParkingStickerCodeException;
}
