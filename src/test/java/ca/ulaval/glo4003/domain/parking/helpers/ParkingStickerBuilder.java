package ca.ulaval.glo4003.domain.parking.helpers;

import static ca.ulaval.glo4003.domain.account.helpers.AccountObjectMother.createAccountId;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaObjectMother.createParkingAreaCode;

import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingSticker;

public class ParkingStickerBuilder {
  private AccountId accountId = createAccountId();
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();

  private ParkingStickerBuilder() {}

  public static ParkingStickerBuilder aParkingSticker() {
    return new ParkingStickerBuilder();
  }

  public ParkingSticker build() {
    return new ParkingSticker(accountId, parkingAreaCode);
  }
}
