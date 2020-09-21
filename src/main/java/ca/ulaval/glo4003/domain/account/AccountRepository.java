package ca.ulaval.glo4003.domain.account;


public interface AccountRepository {
  void save(Account account);

  Account findById(AccountId id);
}
