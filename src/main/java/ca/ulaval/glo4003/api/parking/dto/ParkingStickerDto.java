package ca.ulaval.glo4003.api.parking.dto;

public class ParkingStickerDto {
  public String accountId;
  public String parkingArea;
  public String receptionMethod;
  public String address;

  @Override
  public String toString() {
    return String.format(
        "ParkingStickerDto{accountId='%s', parkingArea='%s', receptionMethod='%s', address='%s'}",
        accountId, parkingArea, receptionMethod, address);
  }
}
