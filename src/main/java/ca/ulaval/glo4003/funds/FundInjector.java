package ca.ulaval.glo4003.funds;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;

public class FundInjector {

  public MoneyAssembler createMoneyAssembler() {
    return new MoneyAssembler();
  }
}
