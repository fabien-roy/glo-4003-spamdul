package ca.ulaval.glo4003.funds.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.Money;

public class BillBuilder {
  private Money amountDue = createMoney();

  public static BillBuilder aBill() {
    return new BillBuilder();
  }

  public Bill build() {
    return new Bill(amountDue);
  }
}
