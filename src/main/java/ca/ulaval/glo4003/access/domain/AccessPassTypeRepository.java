package ca.ulaval.glo4003.access.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;

public interface AccessPassTypeRepository {
  ConsumptionTypes save(AccessPassType accessPassType);

  AccessPassType findByConsumptionType(ConsumptionTypes consumptionTypes);
}
