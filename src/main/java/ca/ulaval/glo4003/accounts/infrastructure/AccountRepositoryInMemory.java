package ca.ulaval.glo4003.accounts.infrastructure;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.exceptions.NotFoundAccountException;
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
    Account foundAccount = accounts.get(accountId);

    if (foundAccount == null) throw new NotFoundAccountException();

    return foundAccount;
  }

  @Override
  public void update(Account account) {
    Account foundAccount = findById(account.getId());

    accounts.put(foundAccount.getId(), account);
  }
}
