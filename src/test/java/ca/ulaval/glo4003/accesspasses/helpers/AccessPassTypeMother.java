package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriodInFrench;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypeInFrench;
import ca.ulaval.glo4003.funds.domain.Money;
import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AccessPassTypeMother {
  public static Map<AccessPeriod, Money> createAccessFeeByPeriod() {
    return Arrays.stream(AccessPeriod.class.getEnumConstants())
        .collect(Collectors.toMap(period -> period, period -> createMoney()));
  }

  public static Map<String, Map<String, Double>> createAccessPassDataFromExcelSheet() {
    Map<String, Double> feeByTime = new HashMap<>();
    feeByTime.put(
        randomEnum(AccessPeriodInFrench.class).toString(), Faker.instance().random().nextDouble());
    Map<String, Map<String, Double>> feeByTimeByConsumptionType = new HashMap<>();
    feeByTimeByConsumptionType.put(randomEnum(ConsumptionTypeInFrench.class).toString(), feeByTime);
    return feeByTimeByConsumptionType;
  }
}
