package ca.ulaval.glo4003.access.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Map;

public class AccessPassType {
  private final ConsumptionTypes consumptionTypes;
  private final Map<AccessPeriods, Money> feePerPeriod;

  public AccessPassType(ConsumptionTypes consumptionTypes, Map<AccessPeriods, Money> feePerPeriod) {
    this.consumptionTypes = consumptionTypes;
    this.feePerPeriod = feePerPeriod;
  }

  public ConsumptionTypes getConsumptionTypes() {
    return consumptionTypes;
  }

  public Money getFeeForPeriod(AccessPeriods period) {
    return feePerPeriod.get(period);
  }
}
