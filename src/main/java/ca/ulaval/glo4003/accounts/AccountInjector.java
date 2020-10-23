package ca.ulaval.glo4003.accounts;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountIdGenerator;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.infrastructure.AccountRepositoryInMemory;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.funds.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.assemblers.BillIdAssembler;
import ca.ulaval.glo4003.funds.assemblers.BillPaymentAssembler;
import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.services.BillService;

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

  public AccountIdAssembler createAccountIdAssembler() {
    return new AccountIdAssembler();
  }

  public AccountService createAccountService(BillService billService, CarRepository carRepository) {
    return new AccountService(
        accountRepository,
        new AccountIdAssembler(),
        billService,
        new BillAssembler(),
        new BillIdAssembler(),
        new BillPaymentAssembler(new MoneyAssembler()),
        carRepository,
        new CarAssembler(new LicensePlateAssembler(), new AccountIdAssembler()));
  }
}
