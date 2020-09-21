package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.domain.account.AccountId;

public class ParkingSticker {
  private ParkingStickerCode code;
  private final AccountId accountId;
  private final ParkingAreaCode parkingAreaCode;

  public ParkingSticker(AccountId accountId, ParkingAreaCode parkingAreaCode) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
  }

  public void setCode(ParkingStickerCode code) {
    this.code = code;
  }

  public AccountId getAccountId() {
    return accountId;
  }

  public ParkingAreaCode getParkingAreaCode() {
    return parkingAreaCode;
  }
}
