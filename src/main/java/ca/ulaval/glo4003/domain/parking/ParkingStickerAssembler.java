package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountId;
import java.util.UUID;

public class ParkingStickerAssembler {
  public ParkingSticker create(ParkingStickerDto parkingStickerDto) {
    // TODO : ParkingStickerDto::create(ParkingStickerDto)
    return new ParkingSticker(new AccountId(UUID.randomUUID()));
  }
}
