package ca.ulaval.glo4003.accounts.services;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.services.dto.BillDto;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import java.util.List;

public class AccountService {
  private final AccountRepository accountRepository;
  private final AccountIdConverter accountIdConverter;
  private final BillAssembler billAssembler;

  public AccountService(
      AccountRepository accountRepository,
      AccountIdConverter accountIdConverter,
      BillAssembler billAssembler) {
    this.accountRepository = accountRepository;
    this.accountIdConverter = accountIdConverter;
    this.billAssembler = billAssembler;
  }

  public void addCarToAccount(AccountId id, Car car) {
    Account account = getAccount(id);

    account.saveCar(car);
    accountRepository.update(account);
  }

  public void addBillToAccount(AccountId id, Bill bill) {
    Account account = getAccount(id);

    account.addBill(bill);
    accountRepository.update(account);
  }

  public void addParkingStickerToAccount(AccountId id, ParkingSticker parkingSticker, Bill bill) {
    Account account = getAccount(id);

    account.addParkingSticker(parkingSticker);
    account.addBill(bill);
    accountRepository.update(account);
  }

  public void addAccessPassToAccount(AccountId id, AccessPass accessPass, Bill bill) {
    Account account = getAccount(id);

    account.addAccessPass(accessPass);
    account.addBill(bill);
    accountRepository.update(account);
  }

  public void addOffenseToAccount(AccountId id, Bill bill) {
    Account account = getAccount(id);

    account.addBill(bill);
    accountRepository.update(account);
  }

  public List<BillDto> getBills(String accountId) {
    Account account = getAccount(accountId);
    List<Bill> bills = account.getBills();

    return billAssembler.assemble(bills);
  }

  public Bill getBill(BillId billId) {
    return accountRepository.getBill(billId);
  }

  public Account getAccount(String accountId) {
    AccountId id = accountIdConverter.convert(accountId);
    return getAccount(id);
  }

  public Account getAccount(AccountId accountId) {
    return accountRepository.get(accountId);
  }

  public ParkingSticker getParkingSticker(ParkingStickerCode parkingStickerCode) {
    return accountRepository.getParkingSticker(parkingStickerCode);
  }

  public AccessPass getAccessPass(AccessPassCode accessPassCode) {
    return accountRepository.getAccessPass(accessPassCode);
  }

  public List<AccessPass> getAccessPasses(LicensePlate licensePlate) {
    return accountRepository.getAccessPasses(licensePlate);
  }

  public Car getCar(LicensePlate licensePlate) {
    return accountRepository.getCar(licensePlate);
  }

  public void update(AccessPass accessPass) {
    accountRepository.update(accessPass);
  }

  public void update(Bill bill) {
    accountRepository.update(bill);
  }
}
