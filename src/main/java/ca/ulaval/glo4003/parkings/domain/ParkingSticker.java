package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class ParkingSticker {
  private ParkingStickerCode code;
  private final AccountId accountId;
  private final ParkingAreaCode parkingAreaCode;
  private final ReceptionMethod receptionMethod;
  private PostalCode postalCode;
  private EmailAddress emailAddress;
  private final DayOfWeek validDay;

  public ParkingSticker(
      AccountId accountId,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethod receptionMethod,
      PostalCode postalCode,
      DayOfWeek validDay) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.postalCode = postalCode;
    this.validDay = validDay;
  }

  public ParkingSticker(
      AccountId accountId,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethod receptionMethod,
      EmailAddress emailAddress,
      DayOfWeek validDay) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.emailAddress = emailAddress;
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

  public ReceptionMethod getReceptionMethod() {
    return receptionMethod;
  }

  public PostalCode getPostalCode() {
    return postalCode;
  }

  public EmailAddress getEmailAddress() {
    return emailAddress;
  }

  public DayOfWeek getValidDay() {
    return validDay;
  }

  public boolean validateParkingStickerAreaCode(ParkingAreaCode parkingAreaCode) {
    return getParkingAreaCode().equals(parkingAreaCode);
  }
}
