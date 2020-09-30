package ca.ulaval.glo4003.cars.api;

import static ca.ulaval.glo4003.cars.helpers.CarBuilderDtoBuilder.aCarDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.services.CarService;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarResourceImplementationTest {

  @Mock private CarService carService;

  private CarResource carResource;

  private CarDto carDto;

  @Before
  public void setup() {
    carResource = new CarResourceImplementation(carService);
    carDto = aCarDto().build();
  }

  @Test
  public void whenAddingCar_thenAddCar() {
    carResource.addCar(carDto);

    verify(carService).addCar(carDto);
  }

  @Test
  public void whenAddingCar_thenRespondWithCreatedStatus() {
    Response response = carResource.addCar(carDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }
}
