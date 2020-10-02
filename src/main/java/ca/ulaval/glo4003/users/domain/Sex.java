package ca.ulaval.glo4003.users.domain;

import ca.ulaval.glo4003.users.exceptions.InvalidSexException;
import java.util.HashMap;
import java.util.Map;

public enum Sex {
  M("m"),
  F("f"),
  X("x");

  String sex;
  private static final Map<String, Sex> lookup = new HashMap<>();

  static {
    for (Sex name : Sex.values()) {
      lookup.put(name.toString(), name);
    }
  }

  Sex(String sex) {
    this.sex = sex;
  }

  public static Sex get(String sex) {
    Sex foundType = lookup.get(sex.toLowerCase());

    if (foundType == null) throw new InvalidSexException();

    return foundType;
  }

  @Override
  public String toString() {
    return sex;
  }
}
