package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.location.PostalCode;
import ca.ulaval.glo4003.domain.time.Days;

public class ParkingSticker {
  private ParkingStickerCode code;
  private final AccountId accountId;
  private final ParkingAreaCode parkingAreaCode;
  private final ReceptionMethods receptionMethod;
  private final PostalCode postalCode;
  private final Days validDay;

  public ParkingSticker(
      AccountId accountId,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethods receptionMethod,
      PostalCode postalCode,
      Days validDay) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.postalCode = postalCode;
    this.validDay = validDay;
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

  public PostalCode getPostalCode() {
    return postalCode;
  }

  public Days getValidDay() {
    return validDay;
  }

  public boolean validateParkingStickerDay(Days day) {
    Days validDay = getValidDay();

    if (validDay != day) {
      return false;
    }

    return true;
  }
}
