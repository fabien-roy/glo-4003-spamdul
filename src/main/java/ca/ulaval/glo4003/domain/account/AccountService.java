package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.car.Car;

public class AccountService {

  private final AccountIdAssembler accountIdAssembler;
  private final AccountRepository accountRepository;

  public AccountService(
      AccountIdAssembler accountIdAssembler, AccountRepository accountRepository) {
    this.accountIdAssembler = accountIdAssembler;
    this.accountRepository = accountRepository;
  }

  public void addCarToAccount(String id, Car car) {
    AccountId accountId = accountIdAssembler.assemble(id);
    Account account = accountRepository.findById(accountId);

    account.addCar(car);
    accountRepository.save(account);
  }
}
