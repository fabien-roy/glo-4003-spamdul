package ca.ulaval.glo4003.api.car;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import ca.ulaval.glo4003.domain.car.CarService;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarResourceImplementationTest {

  @Mock private CarService carService;

  private CarDto carDTO;

  private CarResource carResource;

  @Before
  public void setup() {
    carResource = new CarResourceImplementation(carService);
    carDTO = new CarDto("Toyota", "Corolla", 2002, "C4R1SK3WL");
  }

  @Test
  public void whenAddingCar_thenAddCar() {
    carResource.addCar(carDTO);

    verify(carService).addCar(carDTO);
  }

  @Test
  public void whenAddingCar_thenRespondWithCreatedStatus() {
    Response response = carResource.addCar(carDTO);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }
}
