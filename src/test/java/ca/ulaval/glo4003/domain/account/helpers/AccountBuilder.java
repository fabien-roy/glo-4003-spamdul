package ca.ulaval.glo4003.domain.account.helpers;

import static ca.ulaval.glo4003.domain.account.helpers.AccountMother.createAccountId;

import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.user.User;

public class AccountBuilder {
  private AccountId id = createAccountId();
  private User user = null; // TODO : Use a new UserBuilder

  private AccountBuilder() {}

  public static AccountBuilder anAccount() {
    return new AccountBuilder();
  }

  public AccountBuilder withId(AccountId id) {
    this.id = id;
    return this;
  }

  public Account build() {
    Account account = new Account(id, user);
    return account;
  }
}
