package ca.ulaval.glo4003.cars.domain;

import ca.ulaval.glo4003.cars.exceptions.InvalidConsumptionTypeException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public enum ConsumptionTypeInFrench {
  GOURMANDE("gourmande"),
  ECONOMIQUE("économique"),
  HYBRIDE_ECONOMIQUE("hybride économique"),
  SUPER_ECONOMIQUE("super économique"),
  ZERO_POLLUTION("0 pollution");

  String consommationType;
  private static final Map<String, ConsumptionTypeInFrench> lookup = new HashMap<>();

  static {
    for (ConsumptionTypeInFrench name : ConsumptionTypeInFrench.values()) {
      lookup.put(name.toString(), name);
    }
  }

  ConsumptionTypeInFrench(String consumptionType) {
    this.consommationType = consumptionType;
  }

  public static ConsumptionTypeInFrench get(String type) {
    if (type == null) throw new InvalidConsumptionTypeException();

    ConsumptionTypeInFrench foundType = lookup.get(type.toLowerCase());

    if (foundType == null) {
      byte[] consumptionTypeUtf8 = type.getBytes();
      String consumptionFrenchType = new String(consumptionTypeUtf8, StandardCharsets.UTF_8);

      foundType = lookup.get(consumptionFrenchType);

      if (foundType == null) {
        throw new InvalidConsumptionTypeException();
      }
    }

    return foundType;
  }

  @Override
  public String toString() {
    return consommationType;
  }
}
