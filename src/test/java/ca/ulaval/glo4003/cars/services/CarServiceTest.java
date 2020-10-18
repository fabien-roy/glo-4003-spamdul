package ca.ulaval.glo4003.cars.services;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.CarDtoBuilder.aCarDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

  private CarService carService;
  private final AccountId accountId = createAccountId();
  private final CarDto carDto = aCarDto().build();
  private final Car car = aCar().build();

  @Before
  public void setUp() {
    carService = new CarService(carAssembler, carRepository, accountService);

    when(carAssembler.assemble(carDto, accountId.toString())).thenReturn(car);
    when(carRepository.get(car.getLicensePlate())).thenReturn(car);
  }

  @Test
  public void whenAddingCar_thenAssembleCar() {
    carService.addCar(carDto, accountId.toString());

    verify(carAssembler).assemble(carDto, accountId.toString());
  }

  @Test
  public void whenAddingCar_thenAddLicensePlateToAccount() {
    carService.addCar(carDto, accountId.toString());

    verify(accountService).addLicensePlateToAccount(car.getAccountId(), car.getLicensePlate());
  }

  @Test
  public void whenAddingCar_thenSaveToRepository() {
    carService.addCar(carDto, accountId.toString());

    verify(carRepository).save(car);
  }

  @Test
  public void whenGettingCar_thenGetCarInRepository() {
    Car receivedCar = carService.getCar(car.getLicensePlate());

    assertThat(receivedCar).isSameInstanceAs(car);
  }
}
