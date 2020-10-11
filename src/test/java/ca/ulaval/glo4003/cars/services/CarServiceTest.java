package ca.ulaval.glo4003.cars.services;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.CarBuilderDtoBuilder.aCarDto;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

  @Mock private CarAssembler carAssembler;
  @Mock private CarRepository carRepository;
  @Mock private AccountService accountService;
  @Mock private AccountIdAssembler accountIdAssembler;

  private CarService carService;
  private final AccountId accountId = createAccountId();
  private final CarDto carDto = aCarDto().build();
  private final Car car = aCar().build();

  @Before
  public void setup() {
    carService = new CarService(carAssembler, carRepository, accountService, accountIdAssembler);

    when(carAssembler.assemble(carDto, accountId.toString())).thenReturn(car);
  }

  @Test
  public void whenAddingCar_shouldAssembleCar() {
    carService.addCar(carDto, accountId.toString());

    verify(carAssembler).assemble(carDto, accountId.toString());
  }

  @Test
  public void whenAddingCar_shouldAddLicensePlateToAccount() {
    carService.addCar(carDto, accountId.toString());

    verify(accountService).addLicensePlateToAccount(car.getAccountId(), car.getLicensePlate());
  }

  @Test
  public void whenAddingCar_shouldSaveToRepository() {
    carService.addCar(carDto, accountId.toString());

    verify(carRepository).save(car);
  }

  @Test
  public void whenAddingCar_shouldCallAccountIdAssembler() {
    carService.addCar(carDto, accountId.toString());

    verify(accountIdAssembler).assemble(accountId.toString());
  }

  @Test
  public void whenAddingCar_shouldCallGetAccount() {
    when(accountIdAssembler.assemble(accountId.toString())).thenReturn(accountId);

    carService.addCar(carDto, accountId.toString());

    verify(accountService).getAccount(accountId);
  }
}
