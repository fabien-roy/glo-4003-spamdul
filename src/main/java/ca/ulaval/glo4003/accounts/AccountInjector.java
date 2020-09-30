package ca.ulaval.glo4003.accounts;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountIdGenerator;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.infrastructure.AccountRepositoryInMemory;
import ca.ulaval.glo4003.accounts.services.AccountService;

public class AccountInjector {

  private final AccountRepository accountRepository;
  private final AccountIdGenerator accountIdGenerator;

  public AccountInjector() {
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
