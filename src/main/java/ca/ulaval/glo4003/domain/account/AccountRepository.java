package ca.ulaval.glo4003.domain.account;

public interface AccountRepository {
  Account findById(AccountId id);

  void update(Account account);
}
