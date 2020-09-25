package ca.ulaval.glo4003.infrastructure.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeRepository;
import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingStickerCodeException;
import java.util.HashMap;
import java.util.Map;

public class ParkingStickerCodeRepositoryInMemory implements ParkingStickerCodeRepository {
  private final Map<String, ParkingStickerCode> parkingStickerCodes = new HashMap<>();

  @Override
  public ParkingStickerCode findById(ParkingStickerCodeDto code)
      throws NotFoundParkingStickerCodeException {
    ParkingStickerCode foundCode = parkingStickerCodes.get(code);

    if (foundCode != null) {
      return foundCode;
    } else {
      throw new NotFoundParkingStickerCodeException("Parking sticker code not found");
    }
  }
}
