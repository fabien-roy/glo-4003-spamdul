package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.List;

public class BillProfitsCalculator {

  public Money calculate(List<Bill> bills) {
    Money total = Money.ZERO();
    for (Bill bill : bills) {
      total.plus(bill.getAmountPaid());
    }
    return total;
  }
}
