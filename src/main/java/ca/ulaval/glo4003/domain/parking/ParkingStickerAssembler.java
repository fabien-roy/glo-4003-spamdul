package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;

public class ParkingStickerAssembler {
  private final AccountIdAssembler accountIdAssembler;

  public ParkingStickerAssembler(AccountIdAssembler accountIdAssembler) {
    this.accountIdAssembler = accountIdAssembler;
  }

  public ParkingSticker assemble(ParkingStickerDto parkingStickerDto) {
    AccountId accountId = accountIdAssembler.assemble(parkingStickerDto.accountId);

    return new ParkingSticker(
        accountId,
        new ParkingAreaCode(parkingStickerDto.parkingArea),
        ReceptionMethods.get(parkingStickerDto.receptionMethod));
  }
}
