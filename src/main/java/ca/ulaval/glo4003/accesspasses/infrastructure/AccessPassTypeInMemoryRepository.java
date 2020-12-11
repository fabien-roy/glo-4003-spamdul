package ca.ulaval.glo4003.accesspasses.infrastructure;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassType;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassTypeRepository;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidConsumptionTypeException;
import java.util.HashMap;

public class AccessPassTypeInMemoryRepository implements AccessPassTypeRepository {
  private HashMap<ConsumptionType, AccessPassType> accessPassPriceByCarConsumptions =
      new HashMap<>();

  @Override
  public ConsumptionType save(AccessPassType accessPassType) {
    accessPassPriceByCarConsumptions.put(accessPassType.getConsumptionTypes(), accessPassType);
    return accessPassType.getConsumptionTypes();
  }

  @Override
  public AccessPassType findByConsumptionType(ConsumptionType consumptionType) {
    AccessPassType accessPassType = accessPassPriceByCarConsumptions.get(consumptionType);
    if (accessPassType == null) {
      throw new InvalidConsumptionTypeException();
    }

    return accessPassType;
  }
}
