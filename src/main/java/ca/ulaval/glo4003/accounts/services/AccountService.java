package ca.ulaval.glo4003.accounts.services;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;

public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public void addCarToAccount(AccountId id, Car car) {
    Account account = getAccount(id);

    account.addCar(car);
    accountRepository.update(account);
  }

  public void addParkingStickerToAccount(
      AccountId id, ParkingStickerCode parkingStickerCode, BillId billId) {
    Account account = getAccount(id);

    account.addParkingStickerCode(parkingStickerCode);
    account.addBillId(billId);
    accountRepository.update(account);
  }

  private Account getAccount(AccountId id) {
    return accountRepository.findById(id);
  }
}
