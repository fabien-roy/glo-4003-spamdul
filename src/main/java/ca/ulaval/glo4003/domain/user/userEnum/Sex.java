package ca.ulaval.glo4003.domain.user.userEnum;

import ca.ulaval.glo4003.domain.account.AccountValidationError;
import java.util.HashMap;
import java.util.Map;

public enum Sex {
  M("m"),
  MME("mme");

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

  public static Sex get(String name) throws AccountValidationError {
    Sex foundType = lookup.get(name);

    if (foundType == null) throw new AccountValidationError();

    return foundType;
  }

  @Override
  public String toString() {
    return name;
  }
}
