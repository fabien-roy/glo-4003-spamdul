package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.user.User;

public class Account {
  private AccountId id;
  private User user;

  public Account(AccountId id, User user) {
    this.id = id;
    this.user = user;
  }

  public AccountId getId() {
    return id;
  }

  public User getUser() {
    return user;
  }
}
