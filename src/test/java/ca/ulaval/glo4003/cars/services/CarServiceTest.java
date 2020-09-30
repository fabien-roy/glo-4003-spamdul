package ca.ulaval.glo4003.cars.services;

import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.CarBuilderDtoBuilder.aCarDto;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.domain.Car;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

  @Mock private CarAssembler carAssembler;
  @Mock private AccountService accountService;

  private CarService carService;

  private CarDto carDto;
  private Car car;

  @Before
  public void setup() {
    carService = new CarService(carAssembler, accountService);

    carDto = aCarDto().build();
    car = aCar().build();

    when(carAssembler.create(carDto)).thenReturn(car);
  }

  @Test
  public void whenAddingCar_shouldAssembleCar() {
    carService.addCar(carDto);

    verify(carAssembler).create(carDto);
  }

  @Test
  public void whenAddingCar_shouldAddCarToAccount() {
    carService.addCar(carDto);

    verify(accountService).addCarToAccount(carDto.accountId, car);
  }
}
