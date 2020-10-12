package ca.ulaval.glo4003.funds.domain;

import java.util.List;

public interface BillRepository {
  BillId save(Bill bill);

  List<Bill> getBills(List<BillId> billIds);
}
