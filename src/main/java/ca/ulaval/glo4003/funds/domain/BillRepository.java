package ca.ulaval.glo4003.funds.domain;

import java.util.List;

public interface BillRepository<Query extends BillQuery> {
  BillId save(Bill bill);

  List<Bill> getBills(List<BillId> billIds);

  Bill getBill(BillId billId);

  void updateBill(Bill bill);

  List<Bill> getAll(Query billQuery);
}
