package ca.ulaval.glo4003.domain.car;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import ca.ulaval.glo4003.domain.car.LicensePlate.LicensePlate;
import ca.ulaval.glo4003.domain.car.LicensePlate.LicensePlateAssembler;
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
  private LicensePlate licensePlate;

  private CarAssembler carAssembler;
  private CarDto carDTO;
  @Mock private LicensePlateAssembler licensePlateAssembler;

  @Before
  public void setup() {
    licensePlate = new LicensePlate(LICENSE_PLATE);
    carDTO = new CarDto(MANUFACTURER, MODEL, YEAR, LICENSE_PLATE);

    when(licensePlateAssembler.assemble(LICENSE_PLATE)).thenReturn(licensePlate);

    carAssembler = new CarAssembler(licensePlateAssembler);
  }

  @Test
  public void whenCreatingCar_shouldCallLicensePlateAssembler() {
    carAssembler.create(carDTO);

    verify(licensePlateAssembler).assemble(carDTO.licensePlate);
  }

  @Test
  public void whenCreatingCar_shouldReturnACar() {
    Car car = carAssembler.create(carDTO);

    assertThat(car.getManufacturer().equals(MANUFACTURER));
    assertThat(car.getModel().equals(MODEL));
    assertThat(car.getYear() == YEAR);
    assertThat(car.getLicensePlate().equals(licensePlate));
  }
}
