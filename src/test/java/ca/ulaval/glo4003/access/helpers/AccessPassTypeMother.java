package ca.ulaval.glo4003.access.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.access.domain.AccessPeriods;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class AccessPassTypeMother {
  public static Map<AccessPeriods, Money> createAccessFeeByPeriod() {
    return Arrays.stream(AccessPeriods.class.getEnumConstants())
        .collect(Collectors.toMap(period -> period, period -> createMoney()));
  }
}
