package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountId;
import java.util.UUID;

public class ParkingStickerAssembler {
  public ParkingSticker assemble(ParkingStickerDto parkingStickerDto) {
    // TODO : ParkingStickerAssembler::assemble(ParkingStickerDto)
    return new ParkingSticker(new AccountId(UUID.randomUUID()), new ParkingAreaCode("TODO"));
  }
}
