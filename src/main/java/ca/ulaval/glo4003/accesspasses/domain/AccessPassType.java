package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Map;

public class AccessPassType {
  private final ConsumptionType consumptionType;
  private final Map<AccessPeriod, Money> feePerPeriod;

  public AccessPassType(ConsumptionType consumptionType, Map<AccessPeriod, Money> feePerPeriod) {
    this.consumptionType = consumptionType;
    this.feePerPeriod = feePerPeriod;
  }

  public ConsumptionType getConsumptionTypes() {
    return consumptionType;
  }

  public Money getFeeForPeriod(AccessPeriod period) {
    return feePerPeriod.get(period);
  }
}
