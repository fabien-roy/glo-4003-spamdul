package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.domain.user.User;

public class AccountFactory {
  private final AccountIdGenerator accountIdGenerator;

  public AccountFactory(AccountIdGenerator accountIdGenerator) {
    this.accountIdGenerator = accountIdGenerator;
  }

  public Account createAccount(User user) {
    AccountId accountId = accountIdGenerator.generate();

    return new Account(accountId, user);
  }
}
