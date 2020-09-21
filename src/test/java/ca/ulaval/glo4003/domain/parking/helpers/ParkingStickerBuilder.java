package ca.ulaval.glo4003.domain.parking.helpers;

import static ca.ulaval.glo4003.domain.account.helpers.AccountObjectMother.createAccountId;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaObjectMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerObjectMother.createParkingStickerCode;

import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;

public class ParkingStickerBuilder {
  private ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private AccountId accountId = createAccountId();
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();

  private ParkingStickerBuilder() {}

  public static ParkingStickerBuilder aParkingSticker() {
    return new ParkingStickerBuilder();
  }

  public ParkingSticker build() {
    ParkingSticker parkingSticker = new ParkingSticker(accountId, parkingAreaCode);
    parkingSticker.setCode(parkingStickerCode);
    return parkingSticker;
  }
}
