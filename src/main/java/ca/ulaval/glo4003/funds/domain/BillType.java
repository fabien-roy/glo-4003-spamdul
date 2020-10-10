package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.users.exceptions.InvalidSexException;
import java.util.HashMap;
import java.util.Map;

public enum BillType {
  OFFENSE("offense"),
  PARKINGSTICKER("parkingSticker"),
  ACCESSPASS("accessPass");

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

  public static BillType get(String billType) {
    BillType foundType = lookup.get(billType.toLowerCase());

    if (foundType == null) throw new InvalidSexException();

    return foundType;
  }

  @Override
  public String toString() {
    return billType;
  }
}
