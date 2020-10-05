package ca.ulaval.glo4003.funds;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.services.BillService;

public class FundInjector {

  public BillService createBillService() {
    return new BillService();
  }

  public MoneyAssembler createMoneyAssembler() {
    return new MoneyAssembler();
  }
}
