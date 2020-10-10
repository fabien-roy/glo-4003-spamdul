package ca.ulaval.glo4003.access.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriods;
import java.util.Map;

public class AccessPassPriceByCarConsumption {
  private final ConsumptionTypes consumptionTypes;
  private final Map<ParkingPeriods, Money> feePerPeriod;

  public AccessPassPriceByCarConsumption(
      ConsumptionTypes consumptionTypes, Map<ParkingPeriods, Money> feePerPeriod) {
    this.consumptionTypes = consumptionTypes;
    this.feePerPeriod = feePerPeriod;
  }

  public ConsumptionTypes getConsumptionTypes() {
    return consumptionTypes;
  }

  public Money getFeeForPeriod(ParkingPeriods period) {
    return feePerPeriod.get(period);
  }
}
