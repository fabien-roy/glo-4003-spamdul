package ca.ulaval.glo4003.domain.car;

import static ca.ulaval.glo4003.api.car.helpers.CarBuilderDtoBuilder.aCarDto;
import static ca.ulaval.glo4003.domain.car.helpers.LicensePlateMother.createLicensePlate;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarYearException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarAssemblerTest {

  private static final LicensePlate LICENSE_PLATE = createLicensePlate();
  private static final int INVALID_YEAR = 100000;

  private CarAssembler carAssembler;
  private CarDto carDto;
  @Mock private LicensePlateAssembler licensePlateAssembler;

  @Before
  public void setup() {
    carAssembler = new CarAssembler(licensePlateAssembler);

    carDto = aCarDto().withLicensePlate(LICENSE_PLATE.toString()).build();

    when(licensePlateAssembler.assemble(LICENSE_PLATE.toString())).thenReturn(LICENSE_PLATE);
  }

  @Test
  public void whenAssembling_shouldCarWithManufacturer() {
    Car car = carAssembler.create(carDto);

    Truth.assertThat(car.getManufacturer()).isEqualTo(carDto.manufacturer);
  }

  @Test
  public void whenAssembling_shouldCarWithModel() {
    Car car = carAssembler.create(carDto);

    Truth.assertThat(car.getModel()).isEqualTo(carDto.model);
  }

  @Test
  public void whenAssembling_shouldCarWithYear() {
    Car car = carAssembler.create(carDto);

    Truth.assertThat(car.getYear()).isEqualTo(carDto.year);
  }

  @Test
  public void whenAssembling_shouldCarWithLicensePlate() {
    Car car = carAssembler.create(carDto);

    Truth.assertThat(car.getLicensePlate().toString()).isEqualTo(carDto.licensePlate);
  }

  @Test(expected = InvalidCarYearException.class)
  public void givenCarWithInvalidYear_whenAssembling_shouldThrowInvalidYearException() {
    CarDto carDto = aCarDto().withYear(INVALID_YEAR).build();

    carAssembler.create(carDto);
  }
}
