package ca.ulaval.glo4003.access.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;

public interface AccessPassPriceByCarConsumptionRepository {
  ConsumptionTypes save(AccessPassPriceByCarConsumption accessPassPriceByCarConsumption);

  AccessPassPriceByCarConsumption findByConsumptionType(ConsumptionTypes consumptionTypes);
}
