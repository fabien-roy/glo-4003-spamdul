package ca.ulaval.glo4003.parkings.api.dto;

public class ParkingStickerDto {
  public String accountId;
  public String parkingArea;
  public String receptionMethod;
  public String postalCode;
  public String email;
  public String validDay;

  @Override
  public String toString() {
    return String.format(
        "ParkingStickerDto{accountId='%s', parkingArea='%s', receptionMethod='%s', postalCode='%s', email='%s', validDay='%s'}",
        accountId, parkingArea, receptionMethod, postalCode, email, validDay);
  }
}