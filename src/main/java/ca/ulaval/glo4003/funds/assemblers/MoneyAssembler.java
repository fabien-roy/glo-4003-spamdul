package ca.ulaval.glo4003.funds.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.exception.InvalidMoneyException;
import ca.ulaval.glo4003.funds.exception.NegativeMoneyException;

public class MoneyAssembler {

  public Money assemble(double amount) {
    if (amount == 0) {
      throw new InvalidMoneyException();
    } else if (amount < 0) {
      throw new NegativeMoneyException();
    }

    return Money.fromDouble(amount);
  }
}
