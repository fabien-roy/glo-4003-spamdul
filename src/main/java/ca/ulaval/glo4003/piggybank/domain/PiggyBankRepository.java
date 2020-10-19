package ca.ulaval.glo4003.piggybank.domain;

import ca.ulaval.glo4003.funds.domain.Money;

public interface PiggyBankRepository {
  void add(Money money);

  void remove(Money money);

  Money get();
}
