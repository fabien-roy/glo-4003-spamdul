package ca.ulaval.glo4003.funds.infrastructure;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.BillQuery;
import ca.ulaval.glo4003.funds.infrastructure.filters.BillFilterInMemory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BillQueryInMemory implements BillQuery {
  private List<Bill> bills;
  private List<BillFilterInMemory> filters;

  public BillQueryInMemory(List<BillFilterInMemory> filters) {
    this.filters = filters;
  }

  @Override
  public List<Bill> execute() {
    return filter(bills);
  }

  public void setBills(Map<BillId, Bill> bills) {
    this.bills = new ArrayList<>(bills.values());
  }

  private List<Bill> filter(List<Bill> bills) {
    List<Bill> filteredBills = new ArrayList<>(bills);

    for (BillFilterInMemory filter : filters) filteredBills = filter.filter(filteredBills);

    return filteredBills;
  }
}
