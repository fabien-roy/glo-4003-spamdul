package ca.ulaval.glo4003.profits.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;

public class ProfitByConsumptionType {
  ConsumptionType consumptionType;
  Money money;

  public ProfitByConsumptionType(ConsumptionType consumptionType, Money money) {
    this.consumptionType = consumptionType;
    this.money = money;
  }

  public Money getMoney() {
    return money;
  }

  public ConsumptionType getConsumptionType() {
    return consumptionType;
  }
}
