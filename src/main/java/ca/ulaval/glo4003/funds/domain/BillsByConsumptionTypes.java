package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillsByConsumptionTypes {
  private Map<ConsumptionType, List<Bill>> billsByConsumptionsType = new HashMap<>();

  public BillsByConsumptionTypes() {
    for (ConsumptionType consumptionType : ConsumptionType.values()) {
      billsByConsumptionsType.put(consumptionType, new ArrayList<>());
    }
  }

  public void addBillWithConsumptionsType(ConsumptionType consumptionType, Bill bill) {
    List<Bill> bills = billsByConsumptionsType.get(consumptionType);
    bills.add(bill);

    billsByConsumptionsType.put(consumptionType, bills);
  }

  public List<Bill> getBillByConsumptionType(ConsumptionType consumptionType) {
    return billsByConsumptionsType.get(consumptionType);
  }
}
