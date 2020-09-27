package ca.ulaval.glo4003.domain.car;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarAssemblerTest {

  private static final String MANUFACTURER = "Toyota";
  private static final String MODEL = "Camry";
  private static final int YEAR = 1300;
  private static final String LICENSE_PLATE = "SPEED";

  private CarAssembler carAssembler;
  private CarDto carDTO;
  @Mock private CarValidator carValidator;

  @Before
  public void setup() {
    carDTO = new CarDto(MANUFACTURER, MODEL, YEAR, LICENSE_PLATE);

    carAssembler = new CarAssembler(carValidator);
  }

  @Test
  public void whenCreatingCar_shouldCallCarValidator() {
    carAssembler.create(carDTO);

    verify(carValidator).validate(carDTO);
  }

  @Test
  public void whenCreatingCar_shouldReturnACar() {
    Car car = carAssembler.create(carDTO);

    assertThat(car.getManufacturer().equals(MANUFACTURER));
    assertThat(car.getModel().equals(MODEL));
    assertThat(car.getYear() == YEAR);
    assertThat(car.getLicensePlate().equals(LICENSE_PLATE));
  }
}
