package ca.ulaval.glo4003.accounts;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountIdGenerator;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.infrastructure.AccountRepositoryInMemory;
import ca.ulaval.glo4003.accounts.services.AccountService;

public class AccountInjector {

  private final AccountRepository accountRepository = new AccountRepositoryInMemory();
  private final AccountIdGenerator accountIdGenerator = new AccountIdGenerator();
  private final AccountService accountService;

  public AccountInjector() {
    accountService = new AccountService(accountRepository);
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

  public AccountService getAccountService() {
    return accountService;
  }
}
