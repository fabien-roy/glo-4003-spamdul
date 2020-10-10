package ca.ulaval.glo4003.access.helper;

import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionTypes;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createFeePerPeriod;

import ca.ulaval.glo4003.access.domain.AccessPassPriceByCarConsumption;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriods;
import java.util.Map;

public class AccessPassPriceByCarConsumptionBuilder {
  ConsumptionTypes consumptionTypes = createConsumptionTypes();
  private Map<ParkingPeriods, Money> feePerPeriod = createFeePerPeriod();

  public static AccessPassPriceByCarConsumptionBuilder anAccessPassPriceByConsumption() {
    return new AccessPassPriceByCarConsumptionBuilder();
  }

  public AccessPassPriceByCarConsumption build() {
    return new AccessPassPriceByCarConsumption(consumptionTypes, feePerPeriod);
  }
}
