package ca.ulaval.glo4003.funds;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.BillFactory;
import ca.ulaval.glo4003.funds.services.BillService;

public class FundInjector {

  public BillService createBillService() {
    BillFactory billFactory = new BillFactory();
    return new BillService(billFactory);
  }

  public MoneyAssembler createMoneyAssembler() {
    return new MoneyAssembler();
  }
}
