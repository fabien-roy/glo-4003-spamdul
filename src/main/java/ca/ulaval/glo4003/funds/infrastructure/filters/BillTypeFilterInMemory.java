package ca.ulaval.glo4003.funds.infrastructure.filters;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillType;
import java.util.List;
import java.util.stream.Collectors;

public class BillTypeFilterInMemory implements BillFilterInMemory {

  private final BillType billType;

  public BillTypeFilterInMemory(BillType billType) {
    this.billType = billType;
  }

  @Override
  public List<Bill> filter(List<Bill> filteredBills) {
    return filteredBills.stream()
        .filter(bill -> bill.isBillTypeEqual(billType))
        .collect(Collectors.toList());
  }
}
