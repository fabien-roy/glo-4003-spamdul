package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.locations.domain.PostalCode;

public class ParkingSticker {
  private ParkingStickerCode code;
  private final AccountId accountId;
  private final ParkingAreaCode parkingAreaCode;
  private final ReceptionMethods receptionMethod;
  private PostalCode postalCode;
  private EmailAddress emailAddress;

  public ParkingSticker(
      AccountId accountId,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethods receptionMethod,
      PostalCode postalCode) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.postalCode = postalCode;
  }

  public ParkingSticker(
      AccountId accountId,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethods receptionMethod,
      EmailAddress emailAddress) {
    this.accountId = accountId;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.emailAddress = emailAddress;
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

  public EmailAddress getEmailAddress() {
    return emailAddress;
  }

  public boolean validateParkingStickerAreaCode(ParkingAreaCode parkingAreaCode) {
    return getParkingAreaCode().equals(parkingAreaCode);
  }
}
