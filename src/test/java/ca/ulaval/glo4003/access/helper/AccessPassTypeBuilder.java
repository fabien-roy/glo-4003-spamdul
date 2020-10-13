package ca.ulaval.glo4003.access.helper;

import static ca.ulaval.glo4003.access.helper.AccessPassTypeMother.createAccessFeeByPeriod;
import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionTypes;

import ca.ulaval.glo4003.access.domain.AccessPassType;
import ca.ulaval.glo4003.access.domain.AccessPeriods;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Map;

public class AccessPassTypeBuilder {
  private ConsumptionTypes consumptionType = createConsumptionTypes();
  private Map<AccessPeriods, Money> feeByPeriod = createAccessFeeByPeriod();

  public static AccessPassTypeBuilder anAccessPassPriceByConsumption() {
    return new AccessPassTypeBuilder();
  }

  public AccessPassTypeBuilder withConsumptionType(ConsumptionTypes consumptionType) {
    this.consumptionType = consumptionType;
    return this;
  }

  public AccessPassType build() {
    return new AccessPassType(consumptionType, feeByPeriod);
  }
}
