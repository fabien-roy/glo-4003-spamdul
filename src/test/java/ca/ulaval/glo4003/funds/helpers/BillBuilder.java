package ca.ulaval.glo4003.funds.helpers;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createDescription;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.Money;

public class BillBuilder {
  private BillId id = createBillId();
  private String description = createDescription();
  private Money amountDue = createMoney();

  public static BillBuilder aBill() {
    return new BillBuilder();
  }

  public Bill build() {
    return new Bill(id, description, amountDue);
  }
}
