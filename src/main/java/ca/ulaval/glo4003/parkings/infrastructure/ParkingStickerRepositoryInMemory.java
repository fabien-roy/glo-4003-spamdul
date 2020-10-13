package ca.ulaval.glo4003.parkings.infrastructure;

import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;
import java.util.HashMap;
import java.util.Map;

public class ParkingStickerRepositoryInMemory implements ParkingStickerRepository {
  private final Map<ParkingStickerCode, ParkingSticker> parkingStickers = new HashMap<>();

  @Override
  public void save(ParkingSticker parkingSticker) {
    parkingStickers.put(parkingSticker.getCode(), parkingSticker);
  }

  // TODO : Is this still necessary?
  @Override
  public ParkingSticker findByCode(ParkingStickerCode code) throws NotFoundParkingStickerException {
    ParkingSticker foundParkingSticker = parkingStickers.get(code);

    if (foundParkingSticker == null) throw new NotFoundParkingStickerException();

    return foundParkingSticker;
  }
}
