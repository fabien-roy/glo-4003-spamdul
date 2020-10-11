package ca.ulaval.glo4003.funds.infrastructure;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.BillRepository;
import ca.ulaval.glo4003.funds.exception.BillNotFound;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillRepositoryInMemory implements BillRepository {
  private final Map<BillId, Bill> bills = new HashMap<>();

  @Override
  public BillId save(Bill bill) {
    bills.put(bill.getId(), bill);
    return bill.getId();
  }

  @Override
  public List<Bill> getBillsByIds(List<BillId> billIds) {
    return billIds.stream().map(bills::get).collect(Collectors.toList());
  }

  @Override
  public Bill getBill(BillId billId) {
    Bill bill = bills.get(billId);

    if (bill == null) {
      throw new BillNotFound();
    }

    return bill;
  }
}
