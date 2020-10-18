package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;

public interface AccessPassTypeRepository {
  ConsumptionType save(AccessPassType accessPassType);

  AccessPassType findByConsumptionType(ConsumptionType consumptionType);
}
