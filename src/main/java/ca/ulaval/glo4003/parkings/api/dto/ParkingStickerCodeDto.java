package ca.ulaval.glo4003.parkings.api.dto;

public class ParkingStickerCodeDto {
  public String parkingStickerCode;

  @Override
  public String toString() {
    return String.format("ParkingStickerCodeDto{parkingStickerCode='%s'}", parkingStickerCode);
  }
}
