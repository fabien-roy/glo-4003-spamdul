package ca.ulaval.glo4003.infrastructure.parking;

import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;
import java.util.HashMap;
import java.util.Map;

public class ParkingStickerRepositoryInMemory implements ParkingStickerRepository {
  private final Map<ParkingStickerCode, ParkingSticker> parkingStickers = new HashMap<>();

  @Override
  public void save(ParkingSticker parkingSticker) {
    parkingStickers.put(parkingSticker.getCode(), parkingSticker);
  }

  @Override
  public ParkingSticker findByCode(ParkingStickerCode code) {
    return parkingStickers.get(code);
  }
}
