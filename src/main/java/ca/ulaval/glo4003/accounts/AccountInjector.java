package ca.ulaval.glo4003.accounts;

import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountIdGenerator;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.infrastructure.AccountRepositoryInMemory;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.accounts.services.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.services.assemblers.BillIdAssembler;
import ca.ulaval.glo4003.funds.services.assemblers.BillPaymentAssembler;
import ca.ulaval.glo4003.funds.services.assemblers.MoneyAssembler;

public class AccountInjector {

  private final AccountRepository accountRepository = new AccountRepositoryInMemory();
  private final AccountIdGenerator accountIdGenerator = new AccountIdGenerator();

  public AccountInjector() {}

  public AccountRepository getAccountRepository() {
    return accountRepository;
  }

  public AccountFactory createAccountFactory() {
    return new AccountFactory(accountIdGenerator);
  }

  public AccountIdConverter createAccountIdConverter() {
    return new AccountIdConverter();
  }

  public AccountIdAssembler createAccountIdAssembler() {
    return new AccountIdAssembler();
  }

  public AccountService createAccountService(BillService billService) {
    return new AccountService(
        accountRepository,
        new AccountIdConverter(),
        billService,
        new BillAssembler(),
        new BillIdAssembler(),
        new BillPaymentAssembler(new MoneyAssembler()));
  }
}
