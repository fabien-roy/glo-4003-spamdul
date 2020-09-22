package ca.ulaval.glo4003.infrastructure.account;

import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {
  private final Map<AccountId, Account> accounts = new HashMap<>();

  @Override
  public AccountId save(Account account) {
    accounts.put(account.getId(), account);
    return account.getId();
  }

  @Override
  public Account findById(AccountId accountId) {
    return accounts.get(accountId);
  }

  @Override
  public void update(Account account) {
    // TODO : AccountRepositoryInMemory::update(Account)
  }
}
