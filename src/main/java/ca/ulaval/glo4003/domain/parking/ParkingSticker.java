package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.domain.account.AccountId;

public class ParkingSticker {
  private ParkingStickerCode code;
  private ParkingAccessDay parkingAccessDay;
  private final AccountId accountId;
  private final ParkingAreaCode parkingAreaCode;
  private final ReceptionMethods receptionMethod;

  public ParkingSticker(
      AccountId accountId, ParkingAreaCode parkingAreaCode, ReceptionMethods receptionMethod) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
  }

  public ParkingStickerCode getCode() {
    return code;
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

  public ReceptionMethods getReceptionMethod() {
    return receptionMethod;
  }

  public void setParkingAccessDay(ParkingAccessDay parkingAccessDay) {
    this.parkingAccessDay = parkingAccessDay;
  }

  public ParkingAccessDay getParkingStickerDate() {
    return parkingAccessDay;
  }
}
