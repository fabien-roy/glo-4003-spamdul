package ca.ulaval.glo4003.funds.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;

public class MoneyAssembler {

  public Money assemble(double amount) {
    return new Money(amount);
  }
}
