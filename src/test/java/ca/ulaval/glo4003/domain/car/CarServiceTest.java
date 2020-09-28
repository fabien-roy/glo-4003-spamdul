package ca.ulaval.glo4003.domain.car;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import ca.ulaval.glo4003.domain.account.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

  private static final String ACCOUNT_ID = "1";

  private CarService carService;

  @Mock private CarAssembler carAssembler;

  @Mock private AccountService accountService;

  @Mock private CarDto carDTO;

  @Mock private Car car;

  @Before
  public void setup() {
    when(carAssembler.create(carDTO)).thenReturn(car);
    carService = new CarService(carAssembler, accountService);
  }

  @Test
  public void whenAddingCar_shouldAssembleCar() {
    carService.addCar(carDTO);

    verify(carAssembler).create(carDTO);
  }

  @Test
  public void whenAddingCar_shouldAddCarToAccount() {
    carService.addCar(carDTO);

    verify(accountService).addCarToAccount(ACCOUNT_ID, car);
  }
}
