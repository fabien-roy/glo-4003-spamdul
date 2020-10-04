package ca.ulaval.glo4003.offenses.domain;

import ca.ulaval.glo4003.offenses.exceptions.InvalidOffenseCodeException;
import java.util.HashMap;
import java.util.Map;

public enum OffenseCodes {
  ZONE_01("ZONE_01"),
  VIG_01("VIG_01"),
  TEMPS_01("TEMPS_01"),
  ZONE_02("ZONE_02"),
  VIG_02("VIG_02"),
  VIG_03("VIG_03"),
  VIG_04("VIG_04"),
  ZONE_03("ZONE_03"),
  NONE("NONE");

  String code;
  private static final Map<String, OffenseCodes> lookup = new HashMap<>();

  static {
    for (OffenseCodes code : OffenseCodes.values()) {
      lookup.put(code.toString(), code);
    }
  }

  OffenseCodes(String code) {
    this.code = code;
  }

  public static OffenseCodes get(String code) {
    OffenseCodes foundType = lookup.get(code.toUpperCase());

    if (foundType == null) throw new InvalidOffenseCodeException();

    return foundType;
  }

  @Override
  public String toString() {
    return code;
  }
}
