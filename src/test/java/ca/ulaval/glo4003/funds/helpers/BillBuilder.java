package ca.ulaval.glo4003.funds.helpers;

import ca.ulaval.glo4003.funds.domain.Bill;

public class BillBuilder {
  public static BillBuilder aBill() {
    return new BillBuilder();
  }

  public Bill build() {
    return new Bill();
  }
}
