package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.communication.EmailAddress;
import ca.ulaval.glo4003.domain.communication.EmailAddressAssembler;
import ca.ulaval.glo4003.domain.location.PostalCode;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.exception.MissingEmailException;
import ca.ulaval.glo4003.domain.parking.exception.MissingPostalCodeException;
import ca.ulaval.glo4003.domain.time.Days;

public class ParkingStickerAssembler {
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final AccountIdAssembler accountIdAssembler;
  private final PostalCodeAssembler postalCodeAssembler;
  private final EmailAddressAssembler emailAddressAssembler;

  public ParkingStickerAssembler(
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      AccountIdAssembler accountIdAssembler,
      PostalCodeAssembler postalCodeAssembler,
      EmailAddressAssembler emailAddressAssembler) {
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
    this.accountIdAssembler = accountIdAssembler;
    this.postalCodeAssembler = postalCodeAssembler;
    this.emailAddressAssembler = emailAddressAssembler;
  }

  public ParkingSticker assemble(ParkingStickerDto parkingStickerDto) {
    AccountId accountId = accountIdAssembler.assemble(parkingStickerDto.accountId);
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(parkingStickerDto.parkingArea);

    ReceptionMethods receptionMethod = ReceptionMethods.get(parkingStickerDto.receptionMethod);

    switch (receptionMethod) {
      case POSTAL:
        if (parkingStickerDto.postalCode == null) {
          throw new MissingPostalCodeException();
        } else {
          PostalCode postalCode = postalCodeAssembler.assemble(parkingStickerDto.postalCode);

          return new ParkingSticker(
              accountId,
              parkingAreaCode,
              receptionMethod,
              postalCode,
              Days.get(parkingStickerDto.validDay));
        }
      default:
      case EMAIL:
        if (parkingStickerDto.email == null) {
          throw new MissingEmailException();
        } else {
          EmailAddress emailAddress = emailAddressAssembler.assemble(parkingStickerDto.email);

          return new ParkingSticker(
              accountId,
              parkingAreaCode,
              receptionMethod,
              emailAddress,
              Days.get(parkingStickerDto.validDay));
        }
    }
  }
}
