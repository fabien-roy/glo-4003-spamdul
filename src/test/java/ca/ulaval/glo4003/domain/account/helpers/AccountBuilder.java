package ca.ulaval.glo4003.domain.account.helpers;

import static ca.ulaval.glo4003.domain.account.helpers.AccountObjectMother.createAccountId;

import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountId;

public class AccountBuilder {
  private AccountId accountId = createAccountId();

  private AccountBuilder() {}

  public static AccountBuilder anAccount() {
    return new AccountBuilder();
  }

  public Account build() {
    Account account = new Account();
    account.setId(accountId);
    return account;
  }
}
