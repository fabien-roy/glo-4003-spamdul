package ca.ulaval.glo4003.domain.car;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.Car.dto.CarDTO;
import ca.ulaval.glo4003.domain.Account.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

  private static int ACCOUNT_ID = 1;

  private CarService carService;

  @Mock private CarAssembler carAssembler;

  @Mock private AccountService accountService;

  @Mock private CarDTO carDTO;

  @Mock private Car car;

  @Before
  public void setup() {
    when(carAssembler.createCar(carDTO)).thenReturn(car);
    carService = new CarService(carAssembler, accountService);
  }

  @Test
  public void whenAddingCar_shouldAssembleCar() {
    carService.addCar(ACCOUNT_ID, carDTO);

    verify(carAssembler).createCar(carDTO);
  }

  @Test
  public void whenAddingCar_shouldAddCarToAccount() {
    carService.addCar(ACCOUNT_ID, carDTO);

    verify(accountService).addCarToAccount(ACCOUNT_ID, car);
  }
}
