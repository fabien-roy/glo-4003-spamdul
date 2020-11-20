package ca.ulaval.glo4003.funds.domain;

import java.util.List;

public class BillPriceCalculator {

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
