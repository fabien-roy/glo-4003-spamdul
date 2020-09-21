package ca.ulaval.glo4003.api.parking.dto;

public class ParkingStickerDto {
  public String userId;
  public String parkingArea;
  public String receptionMethod;
  public String address;

  @Override
  public String toString() {
    return String.format(
        "ParkingStickerDto{userId='%s', parkingArea='%s', receptionMethod='%s', address='%s'}",
        userId, parkingArea, receptionMethod, address);
  }
}
