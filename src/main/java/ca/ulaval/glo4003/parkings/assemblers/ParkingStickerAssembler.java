package ca.ulaval.glo4003.parkings.assemblers;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.communications.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.parkings.exceptions.MissingEmailException;
import ca.ulaval.glo4003.parkings.exceptions.MissingPostalCodeException;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

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

    ReceptionMethod receptionMethod = ReceptionMethod.get(parkingStickerDto.receptionMethod);

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
              DayOfWeek.get(parkingStickerDto.validDay));
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
              DayOfWeek.get(parkingStickerDto.validDay));
        }
    }
  }
}
