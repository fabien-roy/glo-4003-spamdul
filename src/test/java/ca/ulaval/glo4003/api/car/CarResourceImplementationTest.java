package ca.ulaval.glo4003.api.car;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.api.car.dto.CarDTO;
import ca.ulaval.glo4003.domain.car.CarService;
import ca.ulaval.glo4003.domain.car.CarValidator;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarResourceImplementationTest {

  private static final int ACCOUNT_ID = 777;
  private static final int STATUS_OK = 200;

  @Mock private CarService carService;

  @Mock private CarValidator carValidator;

  private CarDTO carDTO;

  private CarResourceImplementation carResource;

  @Before
  public void setup() {
    carDTO = new CarDTO("Toyota", "Corolla", 2002, "C4R1SK3WL");
    carResource = new CarResourceImplementation(carService);
  }

  @Test
  public void whenAddingCar_thenAddCar() {
    carResource.addCar(ACCOUNT_ID, carDTO);

    verify(carService).addCar(ACCOUNT_ID, carDTO);
  }

  @Test
  public void whenAddingCar_shouldReturnOk() {
    Response response = carResource.addCar(ACCOUNT_ID, carDTO);

    assertThat(response.getStatus()).isEqualTo(STATUS_OK);
  }
}
