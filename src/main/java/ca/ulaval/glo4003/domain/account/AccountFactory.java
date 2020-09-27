package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.user.User;
import javax.inject.Inject;

public class AccountFactory {
  private final AccountIdGenerator accountIdGenerator;

  @Inject
  public AccountFactory(AccountIdGenerator accountIdGenerator) {
    this.accountIdGenerator = accountIdGenerator;
  }

  public Account createAccount(User user) {
    AccountId accountId = accountIdGenerator.generate();

    return new Account(accountId, user);
  }
}
