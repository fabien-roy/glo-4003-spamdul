package ca.ulaval.glo4003.funds.infrastructure;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.BillRepository;
import java.util.HashMap;
import java.util.Map;

public class BillRepositoryInMemory implements BillRepository {
  private final Map<BillId, Bill> bills = new HashMap<>();

  @Override
  public BillId save(Bill bill) {
    bills.put(bill.getId(), bill);
    return bill.getId();
  }
}
