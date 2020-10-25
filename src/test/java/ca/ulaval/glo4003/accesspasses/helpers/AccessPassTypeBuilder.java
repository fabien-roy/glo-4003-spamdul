package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassTypeMother.createAccessFeeByPeriod;
import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionType;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassType;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Map;

public class AccessPassTypeBuilder {
  private ConsumptionType consumptionType = createConsumptionType();
  private Map<AccessPeriod, Money> feeByPeriod = createAccessFeeByPeriod();

  public static AccessPassTypeBuilder anAccessPassType() {
    return new AccessPassTypeBuilder();
  }

  public AccessPassType build() {
    return new AccessPassType(consumptionType, feeByPeriod);
  }
}
