package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.funds.exception.InvalidBillTypeException;
import java.util.HashMap;
import java.util.Map;

public enum BillType {
  OFFENSE("offense"),
  PARKING_STICKER("parkingSticker"),
  ACCESS_PASS("accessPass");

  String billType;

  private static final Map<String, BillType> lookup = new HashMap<>();

  static {
    for (BillType name : BillType.values()) {
      lookup.put(name.toString(), name);
    }
  }

  BillType(String billType) {
    this.billType = billType;
  }

  @Override
  public String toString() {
    return billType;
  }

  public static BillType get(String name) {
    BillType foundName = lookup.get(name);

    if (foundName == null) throw new InvalidBillTypeException();

    return foundName;
  }
}
