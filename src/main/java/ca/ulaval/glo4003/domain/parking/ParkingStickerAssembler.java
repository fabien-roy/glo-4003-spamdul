package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.location.PostalCode;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.exception.MissingPostalCodeException;
import ca.ulaval.glo4003.domain.time.Days;
import javax.inject.Inject;

public class ParkingStickerAssembler {
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final AccountIdAssembler accountIdAssembler;
  private final PostalCodeAssembler postalCodeAssembler;

  @Inject
  public ParkingStickerAssembler(
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      AccountIdAssembler accountIdAssembler,
      PostalCodeAssembler postalCodeAssembler) {
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
    this.accountIdAssembler = accountIdAssembler;
    this.postalCodeAssembler = postalCodeAssembler;
  }

  public ParkingSticker assemble(ParkingStickerDto parkingStickerDto) {
    ReceptionMethods receptionMethod = ReceptionMethods.get(parkingStickerDto.receptionMethod);
    validateReceptionMethod(receptionMethod, parkingStickerDto.postalCode);

    AccountId accountId = accountIdAssembler.assemble(parkingStickerDto.accountId);
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(parkingStickerDto.parkingArea);
    PostalCode postalCode = postalCodeAssembler.assemble(parkingStickerDto.postalCode);

    return new ParkingSticker(
        accountId,
        parkingAreaCode,
        receptionMethod,
        postalCode,
        Days.get(parkingStickerDto.validDay));
  }

  private void validateReceptionMethod(ReceptionMethods receptionMethod, String postalCode) {
    if (receptionMethod.equals(ReceptionMethods.POSTAL) && postalCode == null) {
      throw new MissingPostalCodeException();
    }
  }
}
