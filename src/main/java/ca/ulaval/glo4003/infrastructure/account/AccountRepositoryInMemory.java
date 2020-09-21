package ca.ulaval.glo4003.infrastructure.account;

import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {
  private final Map<AccountId, Account> users = new HashMap<>();

  @Override
  public AccountId save(Account account) {
    users.put(account.getAccountId(), account);
    return account.getAccountId();
  }

  @Override
  public Account findById(AccountId accountId) {
    return users.get(accountId);
  }

  public Map<AccountId, Account> getUsers() {
    return users;
  }
}
