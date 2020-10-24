package ca.ulaval.glo4003.accounts.services;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.funds.api.dto.BillDto;
import ca.ulaval.glo4003.funds.api.dto.BillPaymentDto;
import ca.ulaval.glo4003.funds.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.assemblers.BillIdAssembler;
import ca.ulaval.glo4003.funds.assemblers.BillPaymentAssembler;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import java.util.List;

public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountIdAssembler accountIdAssembler;
  private final BillService billService;
  private final BillAssembler billAssembler;
  private final BillIdAssembler billIdAssembler;
  private final BillPaymentAssembler billPaymentAssembler;

  public AccountService(
      AccountRepository accountRepository,
      AccountIdAssembler accountIdAssembler,
      BillService billService,
      BillAssembler billAssembler,
      BillIdAssembler billIdAssembler,
      BillPaymentAssembler billPaymentAssembler) {
    this.accountRepository = accountRepository;
    this.accountIdAssembler = accountIdAssembler;
    this.billService = billService;
    this.billAssembler = billAssembler;
    this.billIdAssembler = billIdAssembler;
    this.billPaymentAssembler = billPaymentAssembler;
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

  public List<BillDto> getBills(String accountId) {
    Account account = getAccount(accountId);
    List<BillId> billIds = account.getBillIds();
    List<Bill> bills = billService.getBillsByIds(billIds);

    return billAssembler.assemble(bills);
  }

  public BillDto payBill(BillPaymentDto billPaymentDto, String accountId, String billId) {
    Money amountToPay = billPaymentAssembler.assemble(billPaymentDto);
    BillId billNumber = billIdAssembler.assemble(billId);

    Account account = getAccount(accountId);
    account.verifyAccountHasBillId(billNumber);

    return billService.payBill(billNumber, amountToPay);
  }

  public Account getAccount(String accountId) {
    AccountId id = accountIdAssembler.assemble(accountId);
    return getAccount(id);
  }

  public Account getAccount(AccountId accountId) {
    return accountRepository.findById(accountId);
  }
}
