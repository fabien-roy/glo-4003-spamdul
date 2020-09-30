package ca.ulaval.glo4003.injection.account;

import ca.ulaval.glo4003.domain.account.*;
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

  public AccountService createAccountService() {
    AccountIdAssembler accountIdAssembler = new AccountIdAssembler();

    return new AccountService(accountIdAssembler, accountRepository);
  }
}
