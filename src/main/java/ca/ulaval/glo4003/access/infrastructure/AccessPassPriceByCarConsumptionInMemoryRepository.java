package ca.ulaval.glo4003.access.infrastructure;

import ca.ulaval.glo4003.access.domain.AccessPassPriceByCarConsumption;
import ca.ulaval.glo4003.access.domain.AccessPassPriceByCarConsumptionRepository;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.cars.exceptions.InvalidConsumptionType;
import java.util.HashMap;

public class AccessPassPriceByCarConsumptionInMemoryRepository
    implements AccessPassPriceByCarConsumptionRepository {
  private HashMap<ConsumptionTypes, AccessPassPriceByCarConsumption>
      accessPassPriceByCarConsumptions = new HashMap<>();

  @Override
  public ConsumptionTypes save(AccessPassPriceByCarConsumption accessPassPriceByCarConsumption) {
    accessPassPriceByCarConsumptions.put(
        accessPassPriceByCarConsumption.getConsumptionTypes(), accessPassPriceByCarConsumption);
    return accessPassPriceByCarConsumption.getConsumptionTypes();
  }

  @Override
  public AccessPassPriceByCarConsumption findByConsumptionType(ConsumptionTypes consumptionTypes) {
    AccessPassPriceByCarConsumption accessPassPriceByCarConsumption =
        accessPassPriceByCarConsumptions.get(consumptionTypes);
    if (accessPassPriceByCarConsumption == null) {
      throw new InvalidConsumptionType();
    }

    return accessPassPriceByCarConsumption;
  }
}
