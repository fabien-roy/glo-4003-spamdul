package ca.ulaval.glo4003.cars.services.converters;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypeInFrench;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidConsumptionTypeException;

public class ConsumptionConverter {

  public ConsumptionType convert(ConsumptionTypeInFrench consumptionTypeInFrench) {
    switch (consumptionTypeInFrench) {
      case GOURMANDE:
        return ConsumptionType.GREEDY;
      case ECONOMIQUE:
        return ConsumptionType.ECONOMIC;
      case ZERO_POLLUTION:
        return ConsumptionType.ZERO_POLLUTION;
      case SUPER_ECONOMIQUE:
        return ConsumptionType.SUPER_ECONOMICAL;
      case HYBRIDE_ECONOMIQUE:
        return ConsumptionType.ECONOMICAL_HYBRID;
    }

    throw new InvalidConsumptionTypeException();
  }
}
