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

  private static final String ACCOUNT_ID = "1";
  private static final String MANUFACTURER = "Toyota";
  private static final String MODEL = "Corolla";
  private static final int YEAR = 2000;
  private static final String LICENSE_PLATE = "AASDES";

  @Mock private CarService carService;

  private CarDto carDto;

  private CarResource carResource;

  @Before
  public void setup() {
    carResource = new CarResourceImplementation(carService);
    carDto = new CarDto(ACCOUNT_ID, MANUFACTURER, MODEL, YEAR, LICENSE_PLATE);
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
