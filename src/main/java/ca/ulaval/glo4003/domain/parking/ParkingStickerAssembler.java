package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.AccessStatusDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.location.PostalCode;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.exception.MissingPostalCodeException;
import ca.ulaval.glo4003.domain.time.Days;
import javax.inject.Inject;

public class ParkingStickerAssembler {
  private final AccountIdAssembler accountIdAssembler;
  private final PostalCodeAssembler postalCodeAssembler;

  @Inject
  public ParkingStickerAssembler(
      AccountIdAssembler accountIdAssembler, PostalCodeAssembler postalCodeAssembler) {
    this.accountIdAssembler = accountIdAssembler;
    this.postalCodeAssembler = postalCodeAssembler;
  }

  public ParkingSticker assemble(ParkingStickerDto parkingStickerDto) {
    ReceptionMethods receptionMethod = ReceptionMethods.get(parkingStickerDto.receptionMethod);
    validateReceptionMethod(receptionMethod, parkingStickerDto.postalCode);

    AccountId accountId = accountIdAssembler.assemble(parkingStickerDto.accountId);
    PostalCode postalCode = postalCodeAssembler.assemble(parkingStickerDto.postalCode);

    return new ParkingSticker(
        accountId,
        new ParkingAreaCode(parkingStickerDto.parkingArea),
        receptionMethod,
        postalCode,
        Days.get(parkingStickerDto.validDay));
  }

  private void validateReceptionMethod(ReceptionMethods receptionMethod, String postalCode) {
    if (receptionMethod.equals(ReceptionMethods.POSTAL) && postalCode == null) {
      throw new MissingPostalCodeException();
    }
  }

  public AccessStatusDto assemble(String status) {
    AccessStatusDto accessStatusDto = new AccessStatusDto();
    accessStatusDto.accessStatus = status;
    return accessStatusDto;
  }
}
