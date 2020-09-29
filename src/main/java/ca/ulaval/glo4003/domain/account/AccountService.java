package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.car.Car;
import javax.inject.Inject;

public class AccountService {

  private AccountIdAssembler accountIdAssembler;
  private AccountRepository accountRepository;

  @Inject
  public AccountService(
      AccountIdAssembler accountIdAssembler, AccountRepository accountRepository) {
    this.accountIdAssembler = accountIdAssembler;
    this.accountRepository = accountRepository;
  }

  public void addCarToAccount(String id, Car car) {
    AccountId accountId = accountIdAssembler.assemble(id);
    accountRepository.findById(accountId).addCar(car);
  }
}
