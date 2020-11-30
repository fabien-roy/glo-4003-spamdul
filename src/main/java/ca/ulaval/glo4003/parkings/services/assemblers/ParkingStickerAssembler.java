package ca.ulaval.glo4003.parkings.services.assemblers;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.services.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.locations.services.assemblers.PostalCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.parkings.exceptions.MissingEmailException;
import ca.ulaval.glo4003.parkings.exceptions.MissingPostalCodeException;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerDto;

public class ParkingStickerAssembler {
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final AccountIdConverter accountIdConverter;
  private final PostalCodeAssembler postalCodeAssembler;
  private final EmailAddressAssembler emailAddressAssembler;
  private final ParkingPeriodAssembler parkingPeriodAssembler;

  public ParkingStickerAssembler(
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      AccountIdConverter accountIdConverter,
      PostalCodeAssembler postalCodeAssembler,
      EmailAddressAssembler emailAddressAssembler,
      ParkingPeriodAssembler parkingPeriodAssembler) {
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
    this.accountIdConverter = accountIdConverter;
    this.postalCodeAssembler = postalCodeAssembler;
    this.emailAddressAssembler = emailAddressAssembler;
    this.parkingPeriodAssembler = parkingPeriodAssembler;
  }

  public ParkingSticker assemble(ParkingStickerDto parkingStickerDto) {
    AccountId accountId = accountIdConverter.convert(parkingStickerDto.accountId);
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(parkingStickerDto.parkingArea);

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingStickerDto.parkingPeriod);
    ReceptionMethod receptionMethod = ReceptionMethod.get(parkingStickerDto.receptionMethod);

    switch (receptionMethod) {
      case POSTAL:
        if (parkingStickerDto.postalCode == null) {
          throw new MissingPostalCodeException();
        } else {
          PostalCode postalCode = postalCodeAssembler.assemble(parkingStickerDto.postalCode);
          return new ParkingSticker(
              accountId, parkingAreaCode, receptionMethod, postalCode, parkingPeriod);
        }
      case SSP:
        return new ParkingSticker(accountId, parkingAreaCode, receptionMethod, parkingPeriod);
      default:
      case EMAIL:
        if (parkingStickerDto.email == null) {
          throw new MissingEmailException();
        } else {
          EmailAddress emailAddress = emailAddressAssembler.assemble(parkingStickerDto.email);
          return new ParkingSticker(
              accountId, parkingAreaCode, receptionMethod, emailAddress, parkingPeriod);
        }
    }
  }
}
