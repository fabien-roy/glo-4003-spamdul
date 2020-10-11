package ca.ulaval.glo4003.access.infrastructure;

import ca.ulaval.glo4003.access.domain.AccessPassType;
import ca.ulaval.glo4003.access.domain.AccessPassTypeRepository;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.cars.exceptions.InvalidConsumptionType;
import java.util.HashMap;

public class AccessPassTypeInMemoryRepository implements AccessPassTypeRepository {
  private HashMap<ConsumptionTypes, AccessPassType> accessPassPriceByCarConsumptions =
      new HashMap<>();

  @Override
  public ConsumptionTypes save(AccessPassType accessPassType) {
    accessPassPriceByCarConsumptions.put(accessPassType.getConsumptionTypes(), accessPassType);
    return accessPassType.getConsumptionTypes();
  }

  @Override
  public AccessPassType findByConsumptionType(ConsumptionTypes consumptionTypes) {
    AccessPassType accessPassType = accessPassPriceByCarConsumptions.get(consumptionTypes);
    if (accessPassType == null) {
      throw new InvalidConsumptionType();
    }

    return accessPassType;
  }
}
