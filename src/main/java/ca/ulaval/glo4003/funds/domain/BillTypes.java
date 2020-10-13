package ca.ulaval.glo4003.funds.domain;

public enum BillTypes {
  OFFENSE("offense"),
  PARKING_STICKER("parkingSticker"),
  ACCESS_PASS("accessPass");

  String billType;

  BillTypes(String billType) {
    this.billType = billType;
  }

  @Override
  public String toString() {
    return billType;
  }
}
