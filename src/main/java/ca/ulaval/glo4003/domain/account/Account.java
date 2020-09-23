package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.user.User;

public class Account {
  private AccountId accountId;
  private User user;

  public Account(AccountId accountId, User user) {
    this.accountId = accountId;
    this.user = user;
  }

  public AccountId getAccountId() {
    return this.accountId;
  }

  public User getUser() {
    return user;
  }
}
