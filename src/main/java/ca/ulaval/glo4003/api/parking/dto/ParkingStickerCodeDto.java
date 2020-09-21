package ca.ulaval.glo4003.api.parking.dto;

public class ParkingStickerCodeDto {
  public String code;

  @Override
  public String toString() {
    return String.format("ParkingStickerCodeDto{code='%s'}", code);
  }
}
