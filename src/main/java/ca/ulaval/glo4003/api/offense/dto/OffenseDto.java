package ca.ulaval.glo4003.api.offense.dto;

public class OffenseDto {
  public String parkingStickerCode;
  public String reasonText;
  public String reasonCode;
  public int amount;

  @Override
  public String toString() {
    return String.format(
        "OffenseDto{stickerCode='%s', reasonText='%s', reasonCode='%s', amount='%s'}",
        parkingStickerCode, reasonText, reasonCode, amount);
  }
}
