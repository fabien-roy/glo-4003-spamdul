package ca.ulaval.glo4003.accounts.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accounts.domain.Account;
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
import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  @Mock private AccountRepository accountRepository;
  @Mock private AccountIdConverter accountIdConverter;
  @Mock private BillService billService;
  @Mock private BillAssembler billAssembler;
  @Mock private BillIdConverter billIdConverter;
  @Mock private BillPaymentConverter billPaymentConverter;
  @Mock private BillPaymentDto billPaymentDto;

  private AccountService accountService;

  private final Account account = anAccount().build();
  private final LicensePlate licensePlate = createLicensePlate();
  private final ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private final BillId billId = createBillId();
  private final AccessPass accessPass = anAccessPass().build();
  private final Bill bill = aBill().build();
  private final Account accountWithBill =
      anAccount().withBillIds(Collections.singletonList(bill.getId())).build();
  private final Car car = aCar().build();

  @Before
  public void setUp() {
    accountService =
        new AccountService(
            accountRepository,
            accountIdConverter,
            billService,
            billAssembler,
            billIdConverter,
            billPaymentConverter);

    when(accountRepository.get(account.getId())).thenReturn(account);
    when(accountRepository.getAccessPass(accessPass.getCode())).thenReturn(accessPass);
    when(accountRepository.getAccessPasses(licensePlate))
        .thenReturn(Collections.singletonList(accessPass));
  }

  @Test
  public void whenAddingCar_shouldAddCarToAccount() {
    accountService.addCarToAccount(account.getId(), car);

    assertThat(account.getCars()).contains(car);
  }

  @Test
  public void whenAddingCar_shouldUpdateAccountInRepository() {
    accountService.addCarToAccount(account.getId(), car);

    verify(accountRepository).update(account);
  }

  @Test
  public void whenAddingParkingSticker_shouldAddParkingStickerCodeToAccount() {
    accountService.addParkingStickerToAccount(account.getId(), parkingStickerCode, billId);

    assertThat(account.getParkingStickerCodes()).contains(parkingStickerCode);
  }

  @Test
  public void whenAddingParkingSticker_shouldAddBillIdToAccount() {
    accountService.addParkingStickerToAccount(account.getId(), parkingStickerCode, billId);

    assertThat(account.getBillIds()).contains(billId);
  }

  @Test
  public void whenAddingParkingSticker_shouldUpdateAccountInRepository() {
    accountService.addParkingStickerToAccount(account.getId(), parkingStickerCode, billId);

    verify(accountRepository).update(account);
  }

  @Test
  public void whenAddingAccessCode_shouldAddAccessCodeToAccount() {
    accountService.addAccessPassToAccount(account.getId(), accessPass, billId);

    assertThat(account.getAccessPass(accessPass.getCode())).isSameInstanceAs(accessPass);
  }

  @Test
  public void whenAddingAccessCode_shouldAddBillIdToAccount() {
    accountService.addAccessPassToAccount(account.getId(), accessPass, billId);

    assertThat(account.getBillIds()).contains(billId);
  }

  @Test
  public void whenAddingAccessCode_shouldUpdateAccountInRepository() {
    accountService.addAccessPassToAccount(account.getId(), accessPass, billId);

    verify(accountRepository).update(account);
  }

  @Test
  public void whenAddingOffense_shouldAddBillIdToAccount() {
    accountService.addOffenseToAccount(account.getId(), billId);

    assertThat(account.getBillIds()).contains(billId);
  }

  @Test
  public void whenAddingOffense_shouldUpdateAccountInRepository() {
    accountService.addOffenseToAccount(account.getId(), billId);

    verify(accountRepository).update(account);
  }

  @Test
  public void whenGettingBills_shouldAssembleId() {
    when(accountIdConverter.convert(account.getId().toString())).thenReturn(account.getId());

    accountService.getBills(account.getId().toString());

    verify(accountIdConverter).convert(account.getId().toString());
  }

  @Test
  public void givenBillIds_whenGettingBills_shouldGetBillsToService() {
    List<BillId> billIds = new ArrayList<>();
    billIds.add(bill.getId());

    when(accountIdConverter.convert(accountWithBill.getId().toString()))
        .thenReturn(accountWithBill.getId());
    when(accountRepository.get(accountWithBill.getId())).thenReturn(accountWithBill);

    accountService.getBills(accountWithBill.getId().toString());

    verify(billService).getBillsByIds(billIds);
  }

  @Test
  public void whenGettingBills_shouldGetAccount() {
    when(accountIdConverter.convert(account.getId().toString())).thenReturn(account.getId());

    accountService.getBills(account.getId().toString());

    verify(accountRepository).get(account.getId());
  }

  @Test
  public void givenBills_whenGettingBills_shouldAssembleBillsToBillsDto() {
    List<BillId> billIds = new ArrayList<>();
    billIds.add(bill.getId());
    List<Bill> bills = new ArrayList<>();
    bills.add(bill);

    when(accountIdConverter.convert(accountWithBill.getId().toString()))
        .thenReturn(accountWithBill.getId());
    when(accountRepository.get(accountWithBill.getId())).thenReturn(accountWithBill);
    when(billService.getBillsByIds(billIds)).thenReturn(bills);

    accountService.getBills(accountWithBill.getId().toString());

    verify(billAssembler).assemble(bills);
  }

  @Test
  public void givenAccountWithBill_whenPayingBill_shouldCallPayBillAssembler() {
    setUpPayBill();

    accountService.payBill(
        billPaymentDto, accountWithBill.getId().toString(), bill.getId().toString());

    verify(billPaymentConverter).convert(billPaymentDto);
  }

  @Test
  public void givenAccountWithBill_whenPayingBill_shouldCallAccountIdAssembler() {
    setUpPayBill();

    accountService.payBill(
        billPaymentDto, accountWithBill.getId().toString(), bill.getId().toString());

    verify(accountIdConverter).convert(accountWithBill.getId().toString());
  }

  @Test
  public void givenAccountWithBill_whenPayingBill_shouldCallBillIdAssembler() {
    setUpPayBill();

    accountService.payBill(
        billPaymentDto, accountWithBill.getId().toString(), bill.getId().toString());

    verify(billIdConverter).convert(bill.getId().toString());
  }

  @Test
  public void givenAccountWithBill_whenPayingBill_shouldCallPayBillService() {
    setUpPayBill();

    accountService.payBill(
        billPaymentDto, accountWithBill.getId().toString(), bill.getId().toString());

    verify(billService).payBill(bill.getId(), Money.fromDouble(1));
  }

  @Test
  public void whenGettingAccessPass_thenReturnAccessPass() {
    AccessPass receivedAccessPass = accountService.getAccessPass(accessPass.getCode());

    assertThat(receivedAccessPass).isSameInstanceAs(accessPass);
  }

  @Test
  public void givenLicensePlate_whenGettingAccessPasses_thenReturnAccessPass() {
    List<AccessPass> receivedAccessPasses = accountService.getAccessPasses(licensePlate);

    assertThat(receivedAccessPasses).hasSize(1);
    assertThat(receivedAccessPasses.get(0)).isSameInstanceAs(accessPass);
  }

  @Test
  public void whenUpdatingAccessPass_thenUpdateAccessPassToRepository() {
    accountService.update(accessPass);

    verify(accountRepository).update(accessPass);
  }

  private void setUpPayBill() {
    when(billPaymentConverter.convert(billPaymentDto)).thenReturn(Money.fromDouble(1));
    when(accountIdConverter.convert(accountWithBill.getId().toString()))
        .thenReturn(accountWithBill.getId());
    when(billIdConverter.convert(bill.getId().toString())).thenReturn(bill.getId());
    when(accountRepository.get(accountWithBill.getId())).thenReturn(accountWithBill);
  }
}
