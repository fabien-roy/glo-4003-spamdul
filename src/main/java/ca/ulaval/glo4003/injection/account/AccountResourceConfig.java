package ca.ulaval.glo4003.injection.account;

import ca.ulaval.glo4003.domain.account.AccountFactory;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.account.AccountIdGenerator;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.infrastructure.account.AccountRepositoryInMemory;

public class AccountResourceConfig {

  private final AccountRepository accountRepository;
  private final AccountIdGenerator accountIdGenerator;

  public AccountResourceConfig() {
    accountRepository = new AccountRepositoryInMemory();
    accountIdGenerator = new AccountIdGenerator();
  }

  public AccountRepository getAccountRepository() {
    return accountRepository;
  }

  public AccountFactory createAccountFactory() {
    return new AccountFactory(accountIdGenerator);
  }

  public AccountIdAssembler createAccountIdAssembler() {
    return new AccountIdAssembler();
  }
}
