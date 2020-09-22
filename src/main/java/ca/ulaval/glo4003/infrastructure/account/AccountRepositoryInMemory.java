package ca.ulaval.glo4003.infrastructure.account;

import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountRepository;

public class AccountRepositoryInMemory implements AccountRepository {
  @Override
  public Account findById(AccountId id) {
    // TODO : AccountRepositoryInMemory::findById(AccountId)
    return null;
  }

  @Override
  public void update(Account account) {
    // TODO : AccountRepositoryInMemory::update(Account)
  }
}
