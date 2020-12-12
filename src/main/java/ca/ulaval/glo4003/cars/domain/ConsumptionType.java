package ca.ulaval.glo4003.cars.domain;

import ca.ulaval.glo4003.cars.domain.exceptions.InvalidConsumptionTypeException;
import java.util.HashMap;
import java.util.Map;

public enum ConsumptionType {
  GREEDY("greedy"),
  ECONOMIC("economic"),
  ECONOMICAL_HYBRID("economical hybrid"),
  SUPER_ECONOMICAL("super economical"),
  ZERO_POLLUTION("0 pollution");

  String consumptionType;
  private static final Map<String, ConsumptionType> lookup = new HashMap<>();

  static {
    for (ConsumptionType name : ConsumptionType.values()) {
      lookup.put(name.toString(), name);
    }
  }

  ConsumptionType(String consumptionType) {
    this.consumptionType = consumptionType;
  }

  public static ConsumptionType get(String type) {
    if (type == null) throw new InvalidConsumptionTypeException();

    ConsumptionType foundType = lookup.get(type.toLowerCase());

    if (foundType == null) {
      throw new InvalidConsumptionTypeException();
    }

    return foundType;
  }

  @Override
  public String toString() {
    return consumptionType;
  }
}
