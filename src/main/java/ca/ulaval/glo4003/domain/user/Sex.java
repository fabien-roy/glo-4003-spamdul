package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.domain.user.exception.InvalidSexException;
import java.util.HashMap;
import java.util.Map;

public enum Sex {
  M("m"),
  F("f"),
  X("x");

  String name;
  private static final Map<String, Sex> lookup = new HashMap<>();

  static {
    for (Sex name : Sex.values()) {
      lookup.put(name.toString(), name);
    }
  }

  Sex(String name) {
    this.name = name;
  }

  public static Sex get(String name) {
    Sex foundType = lookup.get(name);

    if (foundType == null) throw new InvalidSexException();

    return foundType;
  }

  @Override
  public String toString() {
    return name;
  }
}
