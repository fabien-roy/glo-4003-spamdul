package ca.ulaval.glo4003.api.parking.dto;

public class ParkingStickerDto {
  public String userId;
  public String parkingArea;
  public String receptionMethod;

  @Override
  public String toString() {
    return String.format(
        "ParkingStickerDto{userId='%s', parkingArea='%s', receptionMethod='%s'}",
        userId, parkingArea, receptionMethod);
  }
}
