package ca.ulaval.glo4003.accesspasses.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassCodeDtoBuilder.anAccessPassCodeDto;
import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassTypeBuilder.anAccessPassType;
import static ca.ulaval.glo4003.accesspasses.helpers.BicycleAccessPassDtoBuilder.aBicycleAccessPassDto;
import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.times.helpers.TimePeriodBuilder.aTimePeriod;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.domain.*;
import ca.ulaval.glo4003.accesspasses.services.assemblers.AccessPassCodeAssembler;
import ca.ulaval.glo4003.accesspasses.services.converters.AccessPassConverter;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.services.dto.BicycleAccessPassDto;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.communications.domain.ReceptionMethod;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassServiceTest {
  @Mock private AccessPassConverter accessPassConverter;
  @Mock private AccessPassFactory accessPassFactory;
  @Mock private CarService carService;
  @Mock private ParkingAreaService parkingAreaService;
  @Mock private AccessPassTypeRepository accessPassTypeRepository;
  @Mock private BillService billService;
  @Mock private AccountService accountService;
  @Mock private AccessPassCodeAssembler accessPassCodeAssembler;
  @Mock private AccessPassCreationObserver accessPassCreationObserver;

  private AccessPassService accessPassService;

  private static final LicensePlate LICENSE_PLATE = createLicensePlate();
  private final Account account = anAccount().build();
  private final Car car = aCar().build();
  private final AccessPassType zeroPollutionAccessPassType = anAccessPassType().build();
  private final AccessPassType notZeroPollutionAccessPassType = anAccessPassType().build();
  private final Bill zeroPollutionBill = aBill().build();
  private final Bill notZeroPollutionBill = aBill().build();
  private final AccessPassCodeDto accessPassCodeDto = anAccessPassCodeDto().build();
  private AccessPassDto accessPassDto = anAccessPassDto().build();
  private BicycleAccessPassDto bicycleAccessPassDto = aBicycleAccessPassDto().build();
  private AccessPass accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.EMAIL).build();
  private TimePeriod timePeriod = aTimePeriod().build();

  @Before
  public void setUp() {
    accessPassService =
        new AccessPassService(
            accessPassConverter,
            accessPassFactory,
            carService,
            parkingAreaService,
            accessPassTypeRepository,
            accountService,
            billService,
            accessPassCodeAssembler);

    when(accessPassCodeAssembler.assemble(accessPass.getCode().toString()))
        .thenReturn(accessPass.getCode());
    when(accountService.getAccessPass(accessPass.getCode())).thenReturn(accessPass);
    when(accountService.getAccessPasses(car.getLicensePlate()))
        .thenReturn(Collections.singletonList(accessPass));
  }

  @Test
  public void whenAddingAccessPass_thenAddNotZeroPollutionBillToAccount() {
    givenAccessPassDtoWithLicensePlate(LICENSE_PLATE);

    accessPassService.addAccessPass(accessPassDto, account.getId().toString());

    verify(accountService)
        .addAccessPassToAccount(account.getId(), accessPass, notZeroPollutionBill);
  }

  @Test
  public void whenAddingAccessPass_thenCheckForParkingAreaExistent() {
    givenAccessPassDtoWithLicensePlate(LICENSE_PLATE);

    accessPassService.addAccessPass(accessPassDto, account.getId().toString());

    verify(parkingAreaService).get(accessPass.getParkingAreaCode());
  }

  public void givenNoLicensePlate_whenAddingAccessPass_thenAddZeroPollutionBillToAccount() {
    givenAccessPassDtoWithLicensePlate(null);

    accessPassService.addAccessPass(accessPassDto, account.getId().toString());

    verify(accountService).addAccessPassToAccount(account.getId(), accessPass, zeroPollutionBill);
  }

  @Test
  public void whenAddingAccessPass_thenReturnAccessPassCode() {
    givenAccessPassDtoWithLicensePlate(LICENSE_PLATE);

    AccessPassCodeDto receivedAccessPassCodeDto =
        accessPassService.addAccessPass(accessPassDto, account.getId().toString());

    assertThat(receivedAccessPassCodeDto).isSameInstanceAs(accessPassCodeDto);
  }

  @Test
  public void whenAddingBicycleAccessPass_thenReturnAccessPassCode() {
    givenAccessPassDtoWithLicensePlate(LICENSE_PLATE);

    AccessPassCodeDto receivedAccessPassCodeDto =
        accessPassService.addAccessPass(bicycleAccessPassDto, account.getId().toString());

    assertThat(receivedAccessPassCodeDto).isSameInstanceAs(accessPassCodeDto);
  }

  @Test
  public void
      givenReceptionMethod_whenAddingBicycleAccessPass_thenAccessPassCreationObserversAreNotified() {
    givenAccessPassDtoWithLicensePlate(LICENSE_PLATE);

    accessPassService.register(accessPassCreationObserver);

    accessPassService.addAccessPass(bicycleAccessPassDto, account.getId().toString());

    verify(accessPassCreationObserver).listenAccessPassCreated(accessPass);
  }

  @Test
  public void whenGettingAccessPass_thenReturnAccessPassFromAccountService() {
    AccessPass receivedAccessPass =
        accessPassService.getAccessPass(accessPass.getCode().toString());

    assertThat(receivedAccessPass).isSameInstanceAs(accessPass);
  }

  @Test
  public void givenLicensePlate_whenGettingAccessPasses_thenReturnAccessPassFromAccountService() {
    List<AccessPass> receivedAccessPasses =
        accessPassService.getAccessPasses(car.getLicensePlate());

    assertThat(receivedAccessPasses).hasSize(1);
    assertThat(receivedAccessPasses.get(0)).isSameInstanceAs(accessPass);
  }

  @Test
  public void whenEnteringCampus_thenIsAdmittedOnCampusIsTrue() {
    accessPassService.enterCampus(accessPass);

    assertThat(accessPass.isAdmittedOnCampus()).isTrue();
  }

  @Test
  public void whenEnteringCampus_thenAccountServiceIsUpdated() {
    accessPassService.enterCampus(accessPass);

    verify(accountService).update(accessPass);
  }

  @Test
  public void whenExitingCampus_thenIsAdmittedOnCampusIsFalse() {
    accessPass = anAccessPass().thatEnteredCampus().build();

    accessPassService.exitCampus(accessPass);

    assertThat(accessPass.isAdmittedOnCampus()).isFalse();
  }

  @Test
  public void whenExitingCampus_thenAccountServiceIsUpdated() {
    accessPass = anAccessPass().thatEnteredCampus().build();

    accessPassService.exitCampus(accessPass);

    verify(accountService).update(accessPass);
  }

  private void givenAccessPassDtoWithLicensePlate(LicensePlate licensePlate) {
    String stringLicensePlate = licensePlate == null ? null : licensePlate.toString();
    accessPassDto =
        anAccessPassDto()
            .withLicensePlate(stringLicensePlate)
            .withAccessPeriod(AccessPeriod.ONE_SEMESTER.toString())
            .build();
    accessPass = anAccessPass().withLicensePlate(licensePlate).build();

    setUpMocks();
  }

  private void setUpMocks() {
    when(accessPassConverter.convert(accessPassDto)).thenReturn(accessPass);
    when(accessPassConverter.convert(bicycleAccessPassDto)).thenReturn(accessPass);
    when(accountService.getAccount(account.getId().toString())).thenReturn(account);
    when(carService.getCar(accessPass.getLicensePlate())).thenReturn(car);
    when(accessPassFactory.create(accessPass)).thenReturn(accessPass);
    when(accessPassTypeRepository.findByConsumptionType(ConsumptionType.ZERO_POLLUTION))
        .thenReturn(zeroPollutionAccessPassType);
    when(accessPassTypeRepository.findByConsumptionType(car.getConsumptionType()))
        .thenReturn(notZeroPollutionAccessPassType);
    when(billService.addBillForAccessPass(
            account.getId(),
            zeroPollutionAccessPassType.getFeeForPeriod(accessPass.getAccessPeriod()),
            accessPass,
            car.getConsumptionType()))
        .thenReturn(zeroPollutionBill);
    when(billService.addBillForAccessPass(
            account.getId(),
            notZeroPollutionAccessPassType.getFeeForPeriod(accessPass.getAccessPeriod()),
            accessPass,
            car.getConsumptionType()))
        .thenReturn(notZeroPollutionBill);

    when(accessPassCodeAssembler.assemble(accessPass.getCode())).thenReturn(accessPassCodeDto);
  }
}
