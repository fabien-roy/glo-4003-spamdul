package ca.ulaval.glo4003.domain.parking.helpers;

import static ca.ulaval.glo4003.domain.account.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.domain.location.helpers.PostalCodeMother.createPostalCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.*;
import static ca.ulaval.glo4003.domain.time.helpers.DayMother.createDay;

import ca.ulaval.glo4003.domain.account.AccountId;
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
  private Days validDay = createDay();

  private ParkingStickerBuilder() {}

  public static ParkingStickerBuilder aParkingSticker() {
    return new ParkingStickerBuilder();
  }

  public ParkingSticker build() {
    ParkingSticker parkingSticker =
        new ParkingSticker(accountId, parkingAreaCode, receptionMethod, postalCode, validDay);
    parkingSticker.setCode(parkingStickerCode);
    return parkingSticker;
  }
}
