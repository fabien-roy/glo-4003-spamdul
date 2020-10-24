package ca.ulaval.glo4003.cars.assemblers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.CarDtoBuilder.aCarDto;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.cars.exceptions.InvalidManufacturerException;
import ca.ulaval.glo4003.cars.exceptions.InvalidModelException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarAssemblerTest {

  @Mock private LicensePlateAssembler licensePlateAssembler;
  @Mock private AccountIdAssembler accountIdAssembler;

  private CarAssembler carAssembler;

  private static final LicensePlate LICENSE_PLATE = createLicensePlate();
  private static final AccountId ACCOUNT_ID = createAccountId();
  private static final int INVALID_YEAR = 100000;

  private final CarDto carDto = aCarDto().withLicensePlate(LICENSE_PLATE.toString()).build();
  private final Car car = aCar().build();

  @Before
  public void setUp() {
    carAssembler = new CarAssembler(licensePlateAssembler, accountIdAssembler);

    when(licensePlateAssembler.assemble(LICENSE_PLATE.toString())).thenReturn(LICENSE_PLATE);
    when(accountIdAssembler.assemble(ACCOUNT_ID.toString())).thenReturn(ACCOUNT_ID);
  }

  @Test
  public void whenAssembling_shouldCarWithLicensePlate() {
    Car car = carAssembler.assemble(carDto, ACCOUNT_ID.toString());

    assertThat(car.getLicensePlate().toString()).isEqualTo(carDto.licensePlate);
  }

  @Test
  public void whenAssembling_shouldCarWithManufacturer() {
    Car car = carAssembler.assemble(carDto, ACCOUNT_ID.toString());

    assertThat(car.getManufacturer()).isEqualTo(carDto.manufacturer);
  }

  @Test
  public void whenAssembling_shouldCarWithModel() {
    Car car = carAssembler.assemble(carDto, ACCOUNT_ID.toString());

    assertThat(car.getModel()).isEqualTo(carDto.model);
  }

  @Test
  public void whenAssembling_shouldCarWithYear() {
    Car car = carAssembler.assemble(carDto, ACCOUNT_ID.toString());

    assertThat(car.getYear()).isEqualTo(carDto.year);
  }

  @Test
  public void whenAssembling_shouldCarWithConsumptionType() {
    Car car = carAssembler.assemble(carDto, ACCOUNT_ID.toString());

    assertThat(car.getConsumptionType().toString()).isEqualTo(carDto.consumptionType);
  }

  @Test(expected = InvalidCarYearException.class)
  public void givenCarWithInvalidYear_whenAssembling_shouldThrowInvalidYearException() {
    CarDto carDto = aCarDto().withYear(INVALID_YEAR).build();

    carAssembler.assemble(carDto, ACCOUNT_ID.toString());
  }

  @Test(expected = InvalidCarYearException.class)
  public void givenCarWithNullYear_whenAssembling_shouldThrowInvalidYearException() {
    // The year is parsed to zero when it is fed a null
    CarDto carDto = aCarDto().withYear(0).build();

    carAssembler.assemble(carDto, ACCOUNT_ID.toString());
  }

  @Test(expected = InvalidManufacturerException.class)
  public void
      givenCarWithNullManufacturer_whenAssembling_shouldThrowInvalidManufacturerException() {
    CarDto carDto = aCarDto().withManufacturer(null).build();

    carAssembler.assemble(carDto, ACCOUNT_ID.toString());
  }

  @Test(expected = InvalidModelException.class)
  public void givenCarWithNullModel_whenAssembling_shouldThrowInvalidModelException() {
    CarDto carDto = aCarDto().withModel(null).build();

    carAssembler.assemble(carDto, ACCOUNT_ID.toString());
  }

  @Test
  public void whenAssemblingCar_thenReturnCarDto() {
    CarDto carDto = carAssembler.assemble(car);

    assertThat(carDto.licensePlate).isEqualTo(car.getLicensePlate().toString());
  }

  @Test
  public void whenAssemblingCars_thenReturnCarsDto() {
    List<Car> cars = new ArrayList<>();
    cars.add(car);

    List<CarDto> carsDto = carAssembler.assemble(cars);

    assertThat(carsDto.get(0).licensePlate).isEqualTo(cars.get(0).getLicensePlate().toString());
  }
}
