package ca.ulaval.glo4003.access.helper;

import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionTypes;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnums;

import ca.ulaval.glo4003.access.domain.AccessPassType;
import ca.ulaval.glo4003.access.domain.AccessPeriods;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessPassPriceByCarConsumptionBuilder {
  ConsumptionTypes consumptionTypes = createConsumptionTypes();

  public static AccessPassPriceByCarConsumptionBuilder anAccessPassPriceByConsumption() {
    return new AccessPassPriceByCarConsumptionBuilder();
  }

  public static AccessPeriods createAccessPeriod() {
    return randomEnum(AccessPeriods.class);
  }

  public static Map<AccessPeriods, Money> createAccessFeeByPeriod() {
    List<AccessPeriods> accessPeriods =
        randomEnums(AccessPeriods.class, Faker.instance().number().numberBetween(1, 5));
    Map<AccessPeriods, Money> feePerPeriod = new HashMap<>();
    accessPeriods.forEach(period -> feePerPeriod.put(period, createMoney()));
    return feePerPeriod;
  }

  public AccessPassType build() {
    return new AccessPassType(consumptionTypes, createAccessFeeByPeriod());
  }

  public AccessPassType buildWithConsumptionType(ConsumptionTypes consumptionTypes) {
    return new AccessPassType(consumptionTypes, createAccessFeeByPeriod());
  }
}
