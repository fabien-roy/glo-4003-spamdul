package ca.ulaval.glo4003.initiatives.domain;

import ca.ulaval.glo4003.funds.domain.Money;

public interface InitiativeFundCollector {
  void addAvailableMoney(Money money);
}
