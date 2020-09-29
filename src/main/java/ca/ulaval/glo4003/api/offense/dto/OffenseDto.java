package ca.ulaval.glo4003.api.offense.dto;

public class OffenseDto {
  public String reasonText;
  public String reasonCode;
  public int amount;

  public OffenseDto(String reasonText, String reasonCode, int amount) {
    this.reasonText = reasonText;
    this.reasonCode = reasonCode;
    this.amount = amount;
  }

  @Override
  public String toString() {
    return String.format(
        "OffenseDto{reasonText='%s', reasonCode='%s', amount='%s'}",
        reasonText, reasonCode, amount);
  }
}
