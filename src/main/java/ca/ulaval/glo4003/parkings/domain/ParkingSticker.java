package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.domain.PostalCode;
import ca.ulaval.glo4003.communications.domain.ReceptionMethod;

public class ParkingSticker {
  private ParkingStickerCode code;
  private final AccountId accountId;
  private final ParkingAreaCode parkingAreaCode;
  private final ReceptionMethod receptionMethod;
  private final ParkingPeriod parkingPeriod;
  private PostalCode postalCode;
  private EmailAddress emailAddress;

  public ParkingSticker(
      AccountId accountId,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethod receptionMethod,
      PostalCode postalCode,
      ParkingPeriod parkingPeriod) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.postalCode = postalCode;
    this.parkingPeriod = parkingPeriod;
  }

  public ParkingSticker(
      AccountId accountId,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethod receptionMethod,
      EmailAddress emailAddress,
      ParkingPeriod parkingPeriod) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.emailAddress = emailAddress;
    this.parkingPeriod = parkingPeriod;
  }

  public ParkingSticker(
      AccountId accountId,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethod receptionMethod,
      ParkingPeriod parkingPeriod) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.parkingPeriod = parkingPeriod;
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

  public ParkingPeriod getParkingPeriod() {
    return parkingPeriod;
  }

  public boolean validateParkingStickerAreaCode(ParkingAreaCode parkingAreaCode) {
    return getParkingAreaCode().equals(parkingAreaCode);
  }
}
