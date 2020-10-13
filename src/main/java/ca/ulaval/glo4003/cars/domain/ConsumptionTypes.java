package ca.ulaval.glo4003.cars.domain;

import ca.ulaval.glo4003.cars.exceptions.InvalidConsumptionTypeException;
import java.util.HashMap;
import java.util.Map;

public enum ConsumptionTypes {
  GREEDY("gourmande"),
  ECONOMIC("économique"),
  ECONOMICAL_HYBRID("hybride économique"),
  SUPER_ECONOMICAL("super économique"),
  ZERO_POLLUTION("0 pollution");

  String consumptionType;
  private static final Map<String, ConsumptionTypes> lookup = new HashMap<>();

  static {
    for (ConsumptionTypes name : ConsumptionTypes.values()) {
      lookup.put(name.toString(), name);
    }
  }

  ConsumptionTypes(String consumptionType) {
    this.consumptionType = consumptionType;
  }

  public static ConsumptionTypes get(String type) {
    if (type == null) throw new InvalidConsumptionTypeException();

    ConsumptionTypes foundType = lookup.get(type.toLowerCase());

    if (foundType == null) throw new InvalidConsumptionTypeException();

    return foundType;
  }

  @Override
  public String toString() {
    return consumptionType;
  }
}
