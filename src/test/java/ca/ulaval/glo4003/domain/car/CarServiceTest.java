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

  private static final String AN_ACCOUNT_ID = "1";
  private static final String A_MANUFACTURER = "Kia";
  private static final String A_MODEL = "THEBEST";
  private static final int A_YEAR = 1920;
  private static final String A_LICENSE_PLATE = "HMMMMM";

  private CarService carService;

  @Mock private CarAssembler carAssembler;

  @Mock private AccountService accountService;

  private CarDto carDto;

  @Mock private Car car;

  @Before
  public void setup() {
    carDto = new CarDto(AN_ACCOUNT_ID, A_MANUFACTURER, A_MODEL, A_YEAR, A_LICENSE_PLATE);
    when(carAssembler.create(carDto)).thenReturn(car);
    carService = new CarService(carAssembler, accountService);
  }

  @Test
  public void whenAddingCar_shouldAssembleCar() {
    carService.addCar(carDto);

    verify(carAssembler).create(carDto);
  }

  @Test
  public void whenAddingCar_shouldAddCarToAccount() {
    carService.addCar(carDto);

    verify(accountService).addCarToAccount(AN_ACCOUNT_ID, car);
  }
}
