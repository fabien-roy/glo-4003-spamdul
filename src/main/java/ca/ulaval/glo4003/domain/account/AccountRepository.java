package ca.ulaval.glo4003.domain.account;

public interface AccountRepository {
  AccountId save(Account account);

  Account findById(AccountId id);

  void update(Account account);
}
