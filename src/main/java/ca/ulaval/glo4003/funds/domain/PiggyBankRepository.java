package ca.ulaval.glo4003.funds.domain;

public interface PiggyBankRepository {
  void add(Money money);

  void remove(Money money);

  Money takeAll();

  Money get();
}
