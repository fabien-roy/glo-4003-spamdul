package ca.ulaval.glo4003.domain.parking;

public interface ParkingStickerCodeRepository {
  ParkingStickerCode findById(ParkingStickerCode code) throws NotFoundParkingStickerCodeException;
}
