package ca.ulaval.glo4003.accounts.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.CarDtoBuilder.aCarDto;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensesPlate;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.funds.api.dto.BillPaymentDto;
import ca.ulaval.glo4003.funds.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.assemblers.BillIdAssembler;
import ca.ulaval.glo4003.funds.assemblers.BillPaymentAssembler;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;
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
  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private BillService billService;
  @Mock private BillAssembler billAssembler;
  @Mock private BillIdAssembler billIdAssembler;
  @Mock private BillPaymentAssembler billPaymentAssembler;
  @Mock private BillPaymentDto billPaymentDto;
  @Mock private CarRepository carRepository;
  @Mock private CarAssembler carAssembler;

  private AccountService accountService;

  private final Account account = anAccount().build();
  private final LicensePlate licensePlate = createLicensePlate();
  private final ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private final BillId billId = createBillId();
  private final AccessPassCode accessPassCode = createAccessPassCode();
  private final Bill bill = aBill().build();
  private final Account accountWithBill =
      anAccount().withBillIds(Collections.singletonList(bill.getId())).build();
  private final List<LicensePlate> licensePlates = createLicensesPlate();
  private final Account accountWithLicensePlate =
      anAccount().withLicensePlate(licensePlates).build();
  private final Car carWithLicensePlate = aCar().withLicensePlate(licensePlates.get(0)).build();
  private final CarDto carDto = aCarDto().withLicensePlate(licensePlates.get(0).toString()).build();

  @Before
  public void setUp() {
    accountService =
        new AccountService(
            accountRepository,
            accountIdAssembler,
            billService,
            billAssembler,
            billIdAssembler,
            billPaymentAssembler,
            carRepository,
            carAssembler);

    when(accountRepository.findById(account.getId())).thenReturn(account);
  }

  @Test
  public void whenAddingCar_shouldAddLicensePlateToAccount() {
    accountService.addLicensePlateToAccount(account.getId(), licensePlate);

    assertThat(account.getLicensePlates()).contains(licensePlate);
  }

  @Test
  public void whenAddingCar_shouldUpdateAccountInRepository() {
    accountService.addLicensePlateToAccount(account.getId(), licensePlate);

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
    accountService.addAccessCodeToAccount(account.getId(), accessPassCode, billId);

    assertThat(account.getAccessPassCodes()).contains(accessPassCode);
  }

  @Test
  public void whenAddingAccessCode_shouldAddBillIdToAccount() {
    accountService.addAccessCodeToAccount(account.getId(), accessPassCode, billId);

    assertThat(account.getBillIds()).contains(billId);
  }

  @Test
  public void whenAddingAccessCode_shouldUpdateAccountInRepository() {
    accountService.addAccessCodeToAccount(account.getId(), accessPassCode, billId);

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
    when(accountIdAssembler.assemble(account.getId().toString())).thenReturn(account.getId());

    accountService.getBills(account.getId().toString());

    verify(accountIdAssembler).assemble(account.getId().toString());
  }

  @Test
  public void givenBillIds_whenGettingBills_shouldGetBillsToService() {
    List<BillId> billIds = new ArrayList<>();
    billIds.add(bill.getId());

    when(accountIdAssembler.assemble(accountWithBill.getId().toString()))
        .thenReturn(accountWithBill.getId());
    when(accountRepository.findById(accountWithBill.getId())).thenReturn(accountWithBill);

    accountService.getBills(accountWithBill.getId().toString());

    verify(billService).getBillsByIds(billIds);
  }

  @Test
  public void whenGettingBills_shouldGetAccount() {
    when(accountIdAssembler.assemble(account.getId().toString())).thenReturn(account.getId());

    accountService.getBills(account.getId().toString());

    verify(accountRepository).findById(account.getId());
  }

  @Test
  public void givenBills_whenGettingBills_shouldAssembleBillsToBillsDto() {
    List<BillId> billIds = new ArrayList<>();
    billIds.add(bill.getId());
    List<Bill> bills = new ArrayList<>();
    bills.add(bill);

    when(accountIdAssembler.assemble(accountWithBill.getId().toString()))
        .thenReturn(accountWithBill.getId());
    when(accountRepository.findById(accountWithBill.getId())).thenReturn(accountWithBill);
    when(billService.getBillsByIds(billIds)).thenReturn(bills);

    accountService.getBills(accountWithBill.getId().toString());

    verify(billAssembler).assemble(bills);
  }

  @Test
  public void givenAccountWithBill_whenPayingBill_shouldCallPayBillAssembler() {
    setUpPayBill();

    accountService.payBill(
        billPaymentDto, accountWithBill.getId().toString(), bill.getId().toString());

    verify(billPaymentAssembler).assemble(billPaymentDto);
  }

  @Test
  public void givenAccountWithBill_whenPayingBill_shouldCallAccountIdAssembler() {
    setUpPayBill();

    accountService.payBill(
        billPaymentDto, accountWithBill.getId().toString(), bill.getId().toString());

    verify(accountIdAssembler).assemble(accountWithBill.getId().toString());
  }

  @Test
  public void givenAccountWithBill_whenPayingBill_shouldCallBillIdAssembler() {
    setUpPayBill();

    accountService.payBill(
        billPaymentDto, accountWithBill.getId().toString(), bill.getId().toString());

    verify(billIdAssembler).assemble(bill.getId().toString());
  }

  @Test
  public void givenAccountWithBill_whenPayingBill_shouldCallPayBillService() {
    setUpPayBill();

    accountService.payBill(
        billPaymentDto, accountWithBill.getId().toString(), bill.getId().toString());

    verify(billService).payBill(bill.getId(), Money.fromDouble(1));
  }

  @Test
  public void whenGettingCars_ThenReturnAccountCars() {
    when(accountRepository.findById(accountWithLicensePlate.getId()))
        .thenReturn(accountWithLicensePlate);
    when(accountIdAssembler.assemble(accountWithLicensePlate.getId().toString()))
        .thenReturn(accountWithLicensePlate.getId());

    List<Car> cars = new ArrayList<>();
    cars.add(carWithLicensePlate);
    when(carRepository.getCars(accountWithLicensePlate.getLicensePlates())).thenReturn(cars);

    List<CarDto> carsDto = new ArrayList<>();
    carsDto.add(carDto);
    when(carAssembler.assemble(cars)).thenReturn(carsDto);

    List<CarDto> carsFromService =
        accountService.getCars(accountWithLicensePlate.getId().toString());

    assertThat(accountWithLicensePlate.getLicensePlates().get(0).toString())
        .isEqualTo(carsFromService.get(0).licensePlate);
  }

  private void setUpPayBill() {
    when(billPaymentAssembler.assemble(billPaymentDto)).thenReturn(Money.fromDouble(1));
    when(accountIdAssembler.assemble(accountWithBill.getId().toString()))
        .thenReturn(accountWithBill.getId());
    when(billIdAssembler.assemble(bill.getId().toString())).thenReturn(bill.getId());
    when(accountRepository.findById(accountWithBill.getId())).thenReturn(accountWithBill);
  }
}
