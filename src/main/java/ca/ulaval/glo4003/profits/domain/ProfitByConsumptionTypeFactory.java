package ca.ulaval.glo4003.profits.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillsByConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillProfitsCalculator;
import java.util.ArrayList;
import java.util.List;

public class ProfitByConsumptionTypeFactory {
  private final BillProfitsCalculator billProfitsCalculator;

  public ProfitByConsumptionTypeFactory(BillProfitsCalculator billProfitsCalculator) {
    this.billProfitsCalculator = billProfitsCalculator;
  }

  public List<ProfitByConsumptionType> create(BillsByConsumptionTypes billsByConsumptionTypes) {
    List<ProfitByConsumptionType> profitByConsumptionTypes = new ArrayList<>();
    for (ConsumptionType consumptionType : ConsumptionType.values()) {
      List<Bill> bills = billsByConsumptionTypes.getBillByConsumptionType(consumptionType);
      ProfitByConsumptionType profitByConsumptionType = create(consumptionType, bills);
      profitByConsumptionTypes.add(profitByConsumptionType);
    }

    return profitByConsumptionTypes;
  }

  private ProfitByConsumptionType create(ConsumptionType consumptionType, List<Bill> bills) {
    Money profits = billProfitsCalculator.calculate(bills);
    return new ProfitByConsumptionType(consumptionType, profits);
  }
}
