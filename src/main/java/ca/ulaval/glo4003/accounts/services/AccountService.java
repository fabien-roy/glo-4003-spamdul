package ca.ulaval.glo4003.accounts.services;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.funds.api.dto.BillsDto;
import ca.ulaval.glo4003.funds.assemblers.BillsAssembler;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import java.util.List;

public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountIdAssembler accountIdAssembler;
  private final BillService billService;
  private final BillsAssembler billsAssembler;

  public AccountService(
      AccountRepository accountRepository,
      AccountIdAssembler accountIdAssembler,
      BillService billService,
      BillsAssembler billsAssembler) {
    this.accountRepository = accountRepository;
    this.accountIdAssembler = accountIdAssembler;
    this.billService = billService;
    this.billsAssembler = billsAssembler;
  }

  public void addLicensePlateToAccount(AccountId id, LicensePlate licensePlate) {
    Account account = getAccount(id);

    account.addLicensePlate(licensePlate);
    accountRepository.update(account);
  }

  public void addParkingStickerToAccount(
      AccountId id, ParkingStickerCode parkingStickerCode, BillId billId) {
    Account account = getAccount(id);

    account.addParkingStickerCode(parkingStickerCode);
    account.addBillId(billId);
    accountRepository.update(account);
  }

  public void addAccessCodeToAccount(AccountId id, AccessPassCode accessPassCode, BillId billId) {
    Account account = getAccount(id);

    account.addAccessPassCode(accessPassCode);
    account.addBillId(billId);
    accountRepository.update(account);
  }

  public void addOffenseToAccount(AccountId id, BillId billId) {
    Account account = getAccount(id);

    account.addBillId(billId);
    accountRepository.update(account);
  }

  public BillsDto getBills(String accountId) {
    AccountId id = accountIdAssembler.assemble(accountId);
    Account account = getAccount(id);
    List<BillId> billIds = account.getBillIds();
    List<Bill> bills = billService.getBillsByIds(billIds);

    return billsAssembler.assemble(bills);
  }

  public Account getAccount(AccountId id) {
    return accountRepository.findById(id);
  }
}
