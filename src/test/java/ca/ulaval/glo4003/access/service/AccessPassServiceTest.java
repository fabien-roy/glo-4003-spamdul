package ca.ulaval.glo4003.access.service;

import static ca.ulaval.glo4003.access.helper.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.access.helper.AccessPassCodeDtoBuilder.anAccessPassCodeDtoBuilder;
import static ca.ulaval.glo4003.access.helper.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.access.helper.AccessPassTypeBuilder.anAccessPassPriceByConsumption;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.access.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.access.domain.*;
import ca.ulaval.glo4003.access.services.AccessPassService;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

// TODO : Refactor this test class
@RunWith(MockitoJUnitRunner.class)
public class AccessPassServiceTest {
  @Mock private AccessPassAssembler accessPassAssembler;
  @Mock private AccessPassFactory accessPassFactory;
  @Mock private CarService carService;
  @Mock private AccessPassTypeRepository accessPassTypeRepository;
  @Mock private AccountService accountService;
  @Mock private BillService billService;
  @Mock private AccessPassRepository accessPassRepository;
  @Mock private AccessPassCodeAssembler accessPassCodeAssembler;
  @Mock private AccountIdAssembler accountIdAssembler;

  private final AccessPassDto accessPassDto = anAccessPassDto().build();
  private final AccountId accountId = createAccountId();
  private final AccessPass accessPass = anAccessPass().build();
  private final AccessPass accessPassWithId = accessPass;
  private final AccessPass accessPassWithoutLicense = anAccessPass().withLicensePlate(null).build();
  private final Car car = aCar().build();
  private final AccessPassCodeDto accessPassCodeDto = anAccessPassCodeDtoBuilder().build();
  private Money moneyDue;

  private AccessPassService accessPassService;

  @Before
  public void setUp() {
    accessPassService =
        new AccessPassService(
            accessPassAssembler,
            accessPassFactory,
            carService,
            accessPassTypeRepository,
            accountService,
            billService,
            accessPassRepository,
            accessPassCodeAssembler,
            accountIdAssembler);

    when(accountIdAssembler.assemble(accountId.toString())).thenReturn(accountId);
    when(accessPassAssembler.assemble(accessPassDto, accountId.toString())).thenReturn(accessPass);

    accessPass.setAccessPassCode(new AccessPassCode(UUID.randomUUID().toString()));
    when(accessPassFactory.create(accessPass)).thenReturn(accessPassWithId);

    when(carService.getCarByLicensePlate(accessPassWithId.getLicensePlate())).thenReturn(car);

    AccessPassType accessPassType = anAccessPassPriceByConsumption().build();
    when(accessPassTypeRepository.findByConsumptionType(car.getConsumptionType()))
        .thenReturn(accessPassType);

    moneyDue = accessPassType.getFeeForPeriod(AccessPeriods.ONE_DAY);

    when(accessPassRepository.save(accessPassWithId))
        .thenReturn(accessPassWithId.getAccessPassCode());
    when(accessPassCodeAssembler.assemble(accessPassWithId.getAccessPassCode()))
        .thenReturn(accessPassCodeDto);
  }

  @Test
  public void whenAddingAccessPassWithoutLicensePlate_thenCarServiceIsNotCalled() {
    when(accessPassFactory.create(accessPass)).thenReturn(accessPassWithoutLicense);

    AccessPassType accessPassType =
        anAccessPassPriceByConsumption()
            .withConsumptionType(ConsumptionTypes.ZERO_POLLUTION)
            .build();
    when(accessPassTypeRepository.findByConsumptionType(ConsumptionTypes.ZERO_POLLUTION))
        .thenReturn(accessPassType);

    accessPassService.addAccessPass(accessPassDto, accountId.toString());

    verify(carService, times(0)).getCarByLicensePlate(accessPassWithId.getLicensePlate());
  }

  @Test
  public void whenAddingAccessPassWithLicensePlate_thenConsumptionTypeIsSameAsCar() {
    accessPassService.addAccessPass(accessPassDto, accountId.toString());

    verify(carService, times(1)).getCarByLicensePlate(accessPassWithId.getLicensePlate());
  }

  @Test
  public void whenAddingAccess_thenAccessPassAssemblerIsCalled() {
    accessPassService.addAccessPass(accessPassDto, accountId.toString());

    verify(accessPassAssembler).assemble(accessPassDto, accountId.toString());
  }

  @Test
  public void whenAddingAccess_thenAccessPassFactoryIsCalled() {
    accessPassService.addAccessPass(accessPassDto, accountId.toString());

    verify(accessPassFactory).create(accessPass);
  }

  @Test
  public void whenAddingAccess_thenBillServiceIsCalled() {
    accessPassService.addAccessPass(accessPassDto, accountId.toString());

    verify(billService).addBillForAccessCode(moneyDue, accessPassWithId.getAccessPassCode());
  }

  @Test
  public void whenAddingAccessPass_thenAccessPassIsSavedToRepository() {
    accessPassService.addAccessPass(accessPassDto, accountId.toString());

    verify(accessPassRepository).save(accessPassWithId);
  }
}
