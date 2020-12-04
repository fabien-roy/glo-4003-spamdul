package ca.ulaval.glo4003.offenses.services.dto;

public class OffenseValidationDto {
  public String parkingStickerCode;
  public String parkingArea;
  public String timeOfDay;

  @Override
  public String toString() {
    return String.format(
        "OffenseDto{parkingStickerCode='%s', parkingArea='%s', timeOfDay='%s'}",
        parkingStickerCode, parkingArea, timeOfDay);
  }
}
