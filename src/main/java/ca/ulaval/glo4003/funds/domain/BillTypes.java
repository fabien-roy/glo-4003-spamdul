package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.users.exceptions.InvalidSexException;
import java.util.HashMap;
import java.util.Map;

public enum BillTypes {
  OFFENSE("offense"),
  PARKING_STICKER("parkingSticker"),
  ACCESS_PASS("accessPass");

  String billType;
  private static final Map<String, BillTypes> lookup = new HashMap<>();

  static {
    for (BillTypes name : BillTypes.values()) {
      lookup.put(name.toString(), name);
    }
  }

  BillTypes(String billType) {
    this.billType = billType;
  }

  public static BillTypes get(String billType) {
    BillTypes foundType = lookup.get(billType.toLowerCase());

    if (foundType == null) throw new InvalidSexException();

    return foundType;
  }

  @Override
  public String toString() {
    return billType;
  }
}
