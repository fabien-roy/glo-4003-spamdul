package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class AccessPassTypeMother {
  public static Map<AccessPeriod, Money> createAccessFeeByPeriod() {
    return Arrays.stream(AccessPeriod.class.getEnumConstants())
        .collect(Collectors.toMap(period -> period, period -> createMoney()));
  }
}
