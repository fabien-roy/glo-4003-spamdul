package ca.ulaval.glo4003.funds.helpers;

import static ca.ulaval.glo4003.funds.helpers.BillMother.*;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.times.domain.CustomDateTime;

public class BillBuilder {
  private BillId id = createBillId();
  private BillType billType = createBillType();
  private String description = createDescription();
  private Money amountDue = createMoney();
  private CustomDateTime customDateTime = createDateTime();

  public static BillBuilder aBill() {
    return new BillBuilder();
  }

  public Bill build() {
    return new Bill(id, billType, description, amountDue, customDateTime);
  }
}
