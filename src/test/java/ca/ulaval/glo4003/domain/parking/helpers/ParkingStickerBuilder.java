package ca.ulaval.glo4003.domain.parking.helpers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.domain.communication.helpers.EmailAddressMother.createEmailAddress;
import static ca.ulaval.glo4003.domain.location.helpers.PostalCodeMother.createPostalCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.*;
import static ca.ulaval.glo4003.domain.time.helpers.DayMother.createDay;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.domain.communication.EmailAddress;
import ca.ulaval.glo4003.domain.location.PostalCode;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.parking.ReceptionMethods;
import ca.ulaval.glo4003.domain.time.Days;

public class ParkingStickerBuilder {
  private ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private AccountId accountId = createAccountId();
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();
  private ReceptionMethods receptionMethod = createReceptionMethod();
  private PostalCode postalCode = createPostalCode();
  private EmailAddress emailAddress = createEmailAddress();
  private Days validDay = createDay();

  private ParkingStickerBuilder() {}

  public static ParkingStickerBuilder aParkingSticker() {
    return new ParkingStickerBuilder();
  }

  public ParkingStickerBuilder withValidDay(String validDay) {
    this.validDay = Days.get(validDay);
    return this;
  }

  public ParkingStickerBuilder withReceptionMethod(ReceptionMethods receptionMethod) {
    this.receptionMethod = receptionMethod;
    return this;
  }

  public ParkingSticker build() {
    ParkingSticker parkingSticker;

    switch (receptionMethod) {
      case POSTAL:
        parkingSticker =
            new ParkingSticker(accountId, parkingAreaCode, receptionMethod, postalCode, validDay);
        break;
      default:
      case EMAIL:
        parkingSticker =
            new ParkingSticker(accountId, parkingAreaCode, receptionMethod, emailAddress, validDay);
        break;
    }

    parkingSticker.setCode(parkingStickerCode);
    return parkingSticker;
  }
}
