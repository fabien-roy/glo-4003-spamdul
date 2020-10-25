package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.List;

public class BillProfitsCalculator {

  public Money calculatePaidPrice(List<Bill> bills) {
    Money total = Money.zero();
    for (Bill bill : bills) {
      total = total.plus(bill.getAmountPaid());
    }
    return total;
  }

  public Money calculateTotalPrice(List<Bill> bills) {
    Money total = Money.zero();
    for (Bill bill : bills) {
      total = total.plus(bill.getAmountDue());
      total = total.plus(bill.getAmountPaid());
    }

    return total;
  }
}
