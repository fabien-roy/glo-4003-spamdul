package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.domain.time.Days.getRandomDay;

import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.location.PostalCode;
import ca.ulaval.glo4003.domain.parking.exception.InvalidParkingStickerDayException;
import ca.ulaval.glo4003.domain.time.Days;
import java.util.ArrayList;
import java.util.List;

public class ParkingSticker {
  private ParkingStickerCode code;
  private ParkingAccessDay parkingAccessDay;
  private final AccountId accountId;
  private final ParkingAreaCode parkingAreaCode;
  private final ReceptionMethods receptionMethod;
  private final PostalCode postalCode;
  private final Days validDay;
  private List<ParkingSticker> parkingStickers = new ArrayList<>();

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

  public void addParkingSticker(ParkingSticker parkingSticker) {
    parkingStickers.add(parkingSticker);
  }

  public void validateParkingStickerDay(Days day) {
    Days randomDay = getRandomDay();
    if (day != randomDay) {
      throw new InvalidParkingStickerDayException();
    }
  }
}
