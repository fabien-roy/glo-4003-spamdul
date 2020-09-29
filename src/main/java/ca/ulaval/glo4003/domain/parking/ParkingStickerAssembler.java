package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.Email.EmailAddress;
import ca.ulaval.glo4003.domain.Email.EmailAddressAssembler;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.location.PostalCode;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.exception.MissingEmailAddressException;
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
    ReceptionMethods receptionMethod = ReceptionMethods.get(parkingStickerDto.receptionMethod);
    validateReceptionMethod(
        receptionMethod, parkingStickerDto.postalCode, parkingStickerDto.emailAddress);

    AccountId accountId = accountIdAssembler.assemble(parkingStickerDto.accountId);
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(parkingStickerDto.parkingArea);

    PostalCode postalCode = null; // TODO value should not be null (new empty postalCode?)
    EmailAddress emailAddress = null; // TODO value should not be null (new empty emailAddress?)
    if (parkingStickerDto.receptionMethod.equals(ReceptionMethods.EMAIL.toString())) {
      emailAddress = emailAddressAssembler.assemble(parkingStickerDto.emailAddress);
    } else if (parkingStickerDto.receptionMethod.equals(ReceptionMethods.POSTAL.toString())) {
      postalCode = postalCodeAssembler.assemble(parkingStickerDto.postalCode);
    }

    return new ParkingSticker(
        accountId,
        parkingAreaCode,
        receptionMethod,
        postalCode,
        emailAddress,
        Days.get(parkingStickerDto.validDay));
  }

  private void validateReceptionMethod(
      ReceptionMethods receptionMethod, String postalCode, String emailAddress) {
    if (receptionMethod.equals(ReceptionMethods.POSTAL) && postalCode == null) {
      throw new MissingPostalCodeException();
    }
    if (receptionMethod.equals(ReceptionMethods.EMAIL) && emailAddress == null) {
      throw new MissingEmailAddressException();
    }
  }
}
