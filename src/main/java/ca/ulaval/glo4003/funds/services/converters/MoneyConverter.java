package ca.ulaval.glo4003.funds.services.converters;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.exception.InvalidMoneyException;
import ca.ulaval.glo4003.funds.exception.NegativeMoneyException;

public class MoneyConverter {

  public Money convert(double amount) {
    if (amount == 0) {
      throw new InvalidMoneyException();
    } else if (amount < 0) {
      throw new NegativeMoneyException();
    }

    return Money.fromDouble(amount);
  }
}
