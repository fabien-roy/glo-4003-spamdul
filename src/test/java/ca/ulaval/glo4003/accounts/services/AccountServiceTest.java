package ca.ulaval.glo4003.accounts.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.BillDtoBuilder.aBillDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.exceptions.NotFoundBillException;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.services.dto.BillDto;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
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
  @Mock private BillAssembler billAssembler;

  private AccountService accountService;

  private final Account account = anAccount().build();
  private final LicensePlate licensePlate = createLicensePlate();
  private final ParkingSticker parkingSticker = aParkingSticker().build();
  private final AccessPass accessPass = anAccessPass().build();
  private final Bill bill = aBill().build();
  private final BillDto billDto = aBillDto().build();
  private final Account accountWithBill =
      anAccount().withBills(Collections.singletonList(bill)).build();
  private final Car car = aCar().build();

  @Before
  public void setUp() {
    accountService = new AccountService(accountRepository, accountIdConverter, billAssembler);

    when(accountRepository.get(account.getId())).thenReturn(account);
    when(accountRepository.getAccessPass(accessPass.getCode())).thenReturn(accessPass);
    when(accountRepository.getAccessPasses(licensePlate))
        .thenReturn(Collections.singletonList(accessPass));
    when(accountIdConverter.convert(account.getId().toString())).thenReturn(account.getId());
    when(accountIdConverter.convert(accountWithBill.getId().toString()))
        .thenReturn(accountWithBill.getId());
    when(accountRepository.get(accountWithBill.getId())).thenReturn(accountWithBill);
    when(billAssembler.assemble(Collections.singletonList(bill)))
        .thenReturn(Collections.singletonList(billDto));
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
    accountService.addParkingStickerToAccount(account.getId(), parkingSticker, bill);

    assertThat(parkingSticker)
        .isSameInstanceAs(account.getParkingSticker(parkingSticker.getCode()));
  }

  @Test
  public void whenAddingParkingSticker_shouldAddBillIdToAccount() {
    accountService.addParkingStickerToAccount(account.getId(), parkingSticker, bill);

    assertThat(account.getBills()).contains(bill);
  }

  @Test
  public void whenAddingParkingSticker_shouldUpdateAccountInRepository() {
    accountService.addParkingStickerToAccount(account.getId(), parkingSticker, bill);

    verify(accountRepository).update(account);
  }

  @Test
  public void whenAddingAccessCode_shouldAddAccessCodeToAccount() {
    accountService.addAccessPassToAccount(account.getId(), accessPass, bill);

    assertThat(account.getAccessPass(accessPass.getCode())).isSameInstanceAs(accessPass);
  }

  @Test
  public void whenAddingAccessCode_shouldAddBillIdToAccount() {
    accountService.addAccessPassToAccount(account.getId(), accessPass, bill);

    assertThat(account.getBills()).contains(bill);
  }

  @Test
  public void whenAddingAccessCode_shouldUpdateAccountInRepository() {
    accountService.addAccessPassToAccount(account.getId(), accessPass, bill);

    verify(accountRepository).update(account);
  }

  @Test
  public void whenAddingOffense_shouldAddBillIdToAccount() {
    accountService.addOffenseToAccount(account.getId(), bill);

    assertThat(account.getBills()).contains(bill);
  }

  @Test
  public void whenAddingOffense_shouldUpdateAccountInRepository() {
    accountService.addOffenseToAccount(account.getId(), bill);

    verify(accountRepository).update(account);
  }

  @Test
  public void whenGettingBills_shouldAssembleId() {
    when(accountIdConverter.convert(account.getId().toString())).thenReturn(account.getId());

    accountService.getBills(account.getId().toString());

    verify(accountIdConverter).convert(account.getId().toString());
  }

  @Test
  public void whenGettingBills_shouldGetAccount() {
    when(accountIdConverter.convert(account.getId().toString())).thenReturn(account.getId());

    accountService.getBills(account.getId().toString());

    verify(accountRepository).get(account.getId());
  }

  @Test(expected = NotFoundBillException.class)
  public void givenAccountWithNoBill_whenGettingBill_thenThrowNotFoundBillException() {
    accountService.getBill(account.getId().toString(), bill.getId());
  }

  @Test
  public void whenGettingBill_thenReturnBill() {
    Bill receivedBill = accountService.getBill(accountWithBill.getId().toString(), bill.getId());

    assertThat(receivedBill).isSameInstanceAs(bill);
  }

  @Test
  public void givenBills_whenGettingBills_thenReturnBillDtos() {
    List<BillDto> receivedBillDtos = accountService.getBills(accountWithBill.getId().toString());

    assertThat(receivedBillDtos).hasSize(1);
    assertThat(receivedBillDtos.get(0)).isSameInstanceAs(billDto);
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
}
