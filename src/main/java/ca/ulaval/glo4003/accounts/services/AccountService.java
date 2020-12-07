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
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.services.converters.BillIdConverter;
import ca.ulaval.glo4003.funds.services.converters.BillPaymentConverter;
import ca.ulaval.glo4003.funds.services.dto.BillDto;
import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import java.util.List;

public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountIdConverter accountIdConverter;
  private final BillService billService;
  private final BillAssembler billAssembler;
  private final BillIdConverter billIdConverter;
  private final BillPaymentConverter billPaymentConverter;

  public AccountService(AccountRepository accountRepository, BillService billService) {
    this(
        accountRepository,
        new AccountIdConverter(),
        billService,
        new BillAssembler(),
        new BillIdConverter(),
        new BillPaymentConverter());
  }

  public AccountService(
      AccountRepository accountRepository,
      AccountIdConverter accountIdConverter,
      BillService billService,
      BillAssembler billAssembler,
      BillIdConverter billIdConverter,
      BillPaymentConverter billPaymentConverter) {
    this.accountRepository = accountRepository;
    this.accountIdConverter = accountIdConverter;
    this.billService = billService;
    this.billAssembler = billAssembler;
    this.billIdConverter = billIdConverter;
    this.billPaymentConverter = billPaymentConverter;
  }

  public void addCarToAccount(AccountId id, Car car) {
    Account account = getAccount(id);

    account.saveCar(car);
    accountRepository.update(account);
  }

  public void addParkingStickerToAccount(
      AccountId id, ParkingSticker parkingSticker, BillId billId) {
    Account account = getAccount(id);

    account.addParkingSticker(parkingSticker);
    account.addBillId(billId);
    accountRepository.update(account);
  }

  public void addAccessPassToAccount(AccountId id, AccessPass accessPass, BillId billId) {
    Account account = getAccount(id);

    account.addAccessPass(accessPass);
    account.addBillId(billId);
    accountRepository.update(account);
  }

  public void addOffenseToAccount(AccountId id, BillId billId) {
    Account account = getAccount(id);

    account.addBillId(billId);
    accountRepository.update(account);
  }

  public List<BillDto> getBills(String accountId) {
    Account account = getAccount(accountId);
    List<BillId> billIds = account.getBillIds();
    List<Bill> bills = billService.getBillsByIds(billIds);

    return billAssembler.assemble(bills);
  }

  public BillDto payBill(BillPaymentDto billPaymentDto, String accountId, String billId) {
    Money amountToPay = billPaymentConverter.convert(billPaymentDto);
    BillId billNumber = billIdConverter.convert(billId);

    Account account = getAccount(accountId);
    account.verifyAccountHasBillId(billNumber);

    return billService.payBill(billNumber, amountToPay);
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
}
