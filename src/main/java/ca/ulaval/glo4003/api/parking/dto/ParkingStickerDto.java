package ca.ulaval.glo4003.api.parking.dto;

public class ParkingStickerDto {
  public String accountId;
  public String parkingArea;
  public String receptionMethod;
  public String postalCode;
  public String emailAddress;
  public String validDay;

  @Override
  public String toString() {
    return String.format(
        "ParkingStickerDto{accountId='%s', parkingArea='%s', receptionMethod='%s', postalCode='%s', emailAddress='%s',validDay='%s'}",
        accountId, parkingArea, receptionMethod, postalCode, emailAddress, validDay);
  }
}
