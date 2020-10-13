package ca.ulaval.glo4003.access.helpers;

import static ca.ulaval.glo4003.access.helpers.AccessPassTypeMother.createAccessFeeByPeriod;
import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionTypes;

import ca.ulaval.glo4003.access.domain.AccessPassType;
import ca.ulaval.glo4003.access.domain.AccessPeriods;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Map;

public class AccessPassTypeBuilder {
  private ConsumptionTypes consumptionType = createConsumptionTypes();
  private Map<AccessPeriods, Money> feeByPeriod = createAccessFeeByPeriod();

  public static AccessPassTypeBuilder anAccessPassType() {
    return new AccessPassTypeBuilder();
  }

  public AccessPassType build() {
    return new AccessPassType(consumptionType, feeByPeriod);
  }
}
