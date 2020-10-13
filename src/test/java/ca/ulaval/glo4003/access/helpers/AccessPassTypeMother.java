package ca.ulaval.glo4003.access.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnums;

import ca.ulaval.glo4003.access.domain.AccessPeriods;
import ca.ulaval.glo4003.funds.domain.Money;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessPassTypeMother {
  public static Map<AccessPeriods, Money> createAccessFeeByPeriod() {
    List<AccessPeriods> accessPeriods =
        randomEnums(AccessPeriods.class, Faker.instance().number().numberBetween(1, 5));
    Map<AccessPeriods, Money> feePerPeriod = new HashMap<>();
    accessPeriods.forEach(period -> feePerPeriod.put(period, createMoney()));
    return feePerPeriod;
  }
}
