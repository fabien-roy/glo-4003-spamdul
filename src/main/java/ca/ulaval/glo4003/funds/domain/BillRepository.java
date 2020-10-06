package ca.ulaval.glo4003.funds.domain;

public interface BillRepository {
  BillId save(Bill bill);
}
